package src.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.Validator;

import src.entity.Market;
import src.entity.MarketRepository;
import src.entity.User;
import src.entity.UserRepository;
import src.entity.UserStock;
import src.entity.UserStockRepository;



public class MarketController extends Controller
{
    public void showAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Market m = getRepository(request).findOneById(id);
        request.setAttribute("market", m);

        if(request.getUserPrincipal() != null){
            User user = (User) request.getAttribute("user");
            UserStockRepository stockRepo = ((UserStockRepository) getManager(request).getRepository("UserStock"));
            request.setAttribute("nbStock", stockRepo.countSellable(id, user.getLogin()));
        }


        Map<Integer, Integer> variations = getRepository(request).getVariationsById(id);
        String chartData = "";
        int curHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Integer lastVal = null;

        if(variations.size() > 0){
            for(int i = 1; i <= 24; i++){
                Integer value = variations.get((curHour + i) % 24);

                if(value == null)
                    value = lastVal;

                lastVal = value;

                chartData += "{ x: " + i + ", y: " + value + " },";
            }

            request.setAttribute(
                "chartData",
                chartData.substring(0, chartData.length()-1)
            );
        }


        render(
            "market:show",
            request,
            response,
            "Marché : " + (request.getParameter("rev") != null ? m.getTitleRev() : m.getTitle())
        );
    }

    public void createAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(request.getMethod().equals("POST")){
            Market m = new Market();

            try {
                m.hydrate(request);
            } catch(java.text.ParseException e){}

            Validator v = m.validate();

            if(v.isValid()){
                m.setMaker(((User) request.getAttribute("user")).getLogin());
                int id = (Integer) getRepository(request).create(m);
                addFlash(
                    request,
                    "success",
                    "Marché créé avec succès"
                );
                redirect(response, request.getContextPath() + "/market/show?id=" + id);
            } else {
                request.setAttribute("bodyClass", "w50");
                request.setAttribute("errors", v);
                addFlash(
                    request,
                    "error",
                    "Le formulaire contient des erreurs"
                );
                flushFlashBag(request);
                render("market:create", request, response, "Créer un marché");
            }
        } else {
            request.setAttribute("bodyClass", "w50");
            render("market:create", request, response, "Créer un marché");
        }
    }

    public void buyAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Récupèrer l'id du market
        int marketId = Integer.parseInt(request.getParameter("id"));
        boolean rev  = request.getParameter("rev") != null;

        if(request.getMethod().equals("POST")){
            // Récupérer l'User
            User user = (User) request.getAttribute("user");

            // Créer un objet UserStock
            UserStock stock = new UserStock();
            stock.setMarketId(marketId);
            stock.setLogin(getUser(request).getLogin());
            stock.setAssertion(!rev);

            // Récupérer les valeurs du formulaire
            stock.hydrate(request);
            Validator v = stock.validate();

            if(v.isValid()){

                // Récupérer les Repository nécessaires
                UserRepository userRepo       = ((UserRepository) getManager(request).getRepository("User"));
                UserStockRepository stockRepo = ((UserStockRepository) getManager(request).getRepository("UserStock"));

                // Rechercher les UserStock inverses dont le prix est inférieur au prix de l'UserStock en création
                List<UserStock> purchasable   = stockRepo.findPurchasable(marketId, stock.getAssertion(), stock.getPrice(), user.getLogin());

                int qty = 0;
                int cashVariation = 0;

                // Pour chaque UserStock capable de vendre au prix demandé
                for(UserStock us : purchasable){
                    if(stock.getNbStock() - stock.getNbSold() <= 0)
                        break;

                    // On protège les erreurs dues au manque de cash
                    try {
                        // Augmenter la valeur nbSold du UserStock débiteur d'autant que possible
                        qty = us.getNbStock() - us.getNbSold();
                        if(qty > stock.getNbStock() - stock.getNbSold())
                            qty = stock.getNbStock() - stock.getNbSold();
                        stockRepo.addToNbSold(us.getStockId(), qty);

                        // Augmenter la valeur nbBuy du UserStock en création de cette même valeur
                        stock.addNbSold(qty);

                        // Retirer price * cette valeur au cash de l'User
                        cashVariation -= qty * us.getPrice();

                        // Retirer price * cette valeur au cash de l'acheteur inverse
                        userRepo.addToCash(us.getLogin(), -qty * us.getPrice());

                        // Enregistre cette transaction dans les variations
                        getRepository(request).addVariation(marketId, us.getPrice(), qty);
                    } catch(Exception e){}
                }

                // Mettre à jour le cash de l'User
                userRepo.addToCash(user.getLogin(), cashVariation);

                // Sauvegarder le nouvel UserStock
                stockRepo.create(stock);

                // Afficher un message de confirmation
                int diff = stock.getNbStock() - stock.getNbSold();
                addFlash(
                    request,
                    "success",
                    "Vous avez acheté " + stock.getNbStock() + " titre" + ((stock.getNbStock() > 1) ? "s" : "") + " " +
                        (diff > 0 ? "(dont " + diff + " en attente) " : "") +
                        "pour un total maximal de " + (stock.getNbStock() * stock.getPrice()) + "€"
                );
            } else {
                request.setAttribute("errors", v);
                addFlash(
                    request,
                    "error",
                    "Le formulaire contient des erreurs"
                );
            }
        }

        // Rediriger vers la page du marché
        redirect(response, request.getContextPath() + "/market/show?id=" + marketId + (rev ? "&rev=true" : ""));
    }

    public void sellAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Récupèrer l'id du market
        int marketId = Integer.parseInt(request.getParameter("id"));
        boolean rev  = request.getParameter("rev") != null;

        if(request.getMethod().equals("POST")){
            // Récupérer l'User
            User user = (User) request.getAttribute("user");

            // Créer un objet UserStock
            UserStock stock = new UserStock();
            stock.setMarketId(marketId);
            stock.setLogin(getUser(request).getLogin());
            stock.setAssertion(rev);
            stock.setBuyOrSell("SELL");

            // Récupérer les valeurs du formulaire
            stock.hydrate(request);
            Validator v = stock.validate();

            if(v.isValid()){

                // Récupérer les Repository nécessaires
                UserRepository userRepo       = ((UserRepository) getManager(request).getRepository("User"));
                UserStockRepository stockRepo = ((UserStockRepository) getManager(request).getRepository("UserStock"));

                // Rechercher les UserStock inverses dont le prix est inférieur au prix de l'UserStock en création
                List<UserStock> purchasable   = stockRepo.findPurchasable(marketId, stock.getAssertion(), stock.getPrice(), user.getLogin());

                int qty = 0;
                int cashVariation = 0;

                // Pour chaque UserStock capable de vendre au prix demandé
                for(UserStock us : purchasable){
                    if(stock.getNbStock() - stock.getNbSold() <= 0)
                        break;

                    // On protège les erreurs dues au manque de cash
                    try {
                        // Augmenter la valeur nbSold du UserStock débiteur d'autant que possible
                        qty = us.getNbStock() - us.getNbSold();
                        if(qty > stock.getNbStock() - stock.getNbSold())
                            qty = stock.getNbStock() - stock.getNbSold();
                        stockRepo.addToNbSold(us.getStockId(), qty);

                        // Augmenter la valeur nbBuy du UserStock en création de cette même valeur
                        stock.addNbSold(qty);

                        // Ajoute price * cette valeur au cash de l'User
                        cashVariation += qty * us.getPrice();

                        // Retirer price * cette valeur au cash de l'acheteur inverse
                        userRepo.addToCash(us.getLogin(), -qty * us.getPrice());

                        // Enregistre cette transaction dans les variations
                        getRepository(request).addVariation(marketId, us.getPrice(), qty);
                    } catch(Exception e){}
                }

                // Mettre à jour le cash de l'User
                userRepo.addToCash(user.getLogin(), cashVariation);

                // Sauvegarder le nouvel UserStock
                stockRepo.create(stock);

                // Afficher un message de confirmation
                int diff = stock.getNbStock() - stock.getNbSold();
                addFlash(
                    request,
                    "success",
                    "Vous avez vendu " + stock.getNbStock() + " titre" + ((stock.getNbStock() > 1) ? "s" : "") + " " +
                        (diff > 0 ? "(dont " + diff + " en attente) " : "") +
                        "pour un total maximal de " + (stock.getNbStock() * stock.getPrice()) + "€"
                );
            } else {
                request.setAttribute("errors", v);
                addFlash(
                    request,
                    "error",
                    "Le formulaire contient des erreurs"
                );
            }
        }

        redirect(response, request.getContextPath() + "/market/show?id=" + marketId + (rev ? "&rev=true" : ""));
    }

    public void endAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Récupèrer l'id du market
        int marketId = Integer.parseInt(request.getParameter("id"));

        if(request.getMethod().equals("POST")){
            // Récupérer l'User et du Market
            User user = (User) request.getAttribute("user");
            Market m = getRepository(request).findOneById(marketId);

            // Vérification que le créateur est bien l'user courant
            if(user.getLogin().equals(m.getMaker())){
                // Récupérer les Repository nécessaires
                UserRepository userRepo       = ((UserRepository) getManager(request).getRepository("User"));
                UserStockRepository stockRepo = ((UserStockRepository) getManager(request).getRepository("UserStock"));

                // Récupérer la réponse
                boolean rep  = request.getParameter("yes") != null;

                try {
                    // Mettre à jour le cash des "gagnants"
                    stockRepo.endMarket(marketId, rep);

                    // Désactive le marché et verse la somme due
                    // aux users qui ont des titres
                    getRepository(request).disable(m);
                } catch(Exception e){}

                // Afficher un message de confirmation
                addFlash(
                    request,
                    "success",
                    "Le marché a été terminé avec réponse : " + (rep ? "Oui" : "Non")
                );
            } else {
                addFlash(
                    request,
                    "error",
                    "Ce marché n'est pas le votre"
                );
            }
        }

        // Rediriger vers la page des marchés de l'utilisateur
        redirect(response, request.getContextPath() + "/market/mymarkets");
    }

    public void mymarketsAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Récupérer l'User
        User user = (User) request.getAttribute("user");

        // Récupérer les marchés créés par l'user
        request.setAttribute("markets", getRepository(request).findByMaker(user.getLogin()));

        render("market:mymarkets", request, response, "Mes marchés");
    }

    public void mystocksAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Récupérer l'User
        User user = (User) request.getAttribute("user");

        // Récupérer les marchés pour lesquels l'user a des titres
        request.setAttribute("markets", getRepository(request).findByStockholder(user.getLogin()));

        render("market:mystocks", request, response, "Mes titres");
    }



    private MarketRepository getRepository(HttpServletRequest request) throws Exception
    {
        return ((MarketRepository) getManager(request).getRepository("Market"));
    }
}