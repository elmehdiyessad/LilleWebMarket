package src.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        Market m = getRepository(request).findOneById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("market", m);

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
                UserRepository userRepo = ((UserRepository) getManager(request).getRepository("User"));
                UserStockRepository stockRepo = ((UserStockRepository) getManager(request).getRepository("UserStock"));

                // Rechercher les UserStock inverses dont le prix est inférieur au prix de l'UserStock en création
                ArrayList<UserStock> purchasable = stockRepo.findPurchasable(marketId, stock.getAssertion(), stock.getPrice(), user.getLogin());

                int qty = 0;
                int cashVariation = 0;

                // Pour chaque UserStock capable de vendre au prix demandé
                for(UserStock us : purchasable){
                    if(stock.getNbStock() - stock.getNbSold() <= 0)
                        break;

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
                    "Vous avez acheté " + stock.getNbStock() + " titre" + ((stock.getNbStock() > 1) ? "s" : "") + " " + (diff > 0 ? "(dont " + diff + " en attente) " : "") +
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



    private MarketRepository getRepository(HttpServletRequest request) throws Exception
    {
        return ((MarketRepository) getManager(request).getRepository("Market"));
    }
}