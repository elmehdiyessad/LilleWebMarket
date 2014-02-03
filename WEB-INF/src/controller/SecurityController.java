package src.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;
import framework.Validator;

import src.entity.User;
import src.entity.UserRepository;



public class SecurityController extends Controller
{
    public void indexAction(HttpServletRequest request, HttpServletResponse response)
    {
        if(request.getParameter("error") != null)
            addFlash(
                request,
                "error",
                "Identifiants incorrects"
            );

    	if(request.getUserPrincipal() != null){
            addFlash(request, "error", "Vous êtes déjà connecté !");
    		redirect(response, request.getContextPath());
        } else if(request.getDispatcherType() == javax.servlet.DispatcherType.FORWARD)
            redirect(response, request.getContextPath() + "/login");
        else
        	render("security:login", request, response, "Connexion");
    }

    public void loginAction(HttpServletRequest request, HttpServletResponse response)
    {
        redirect(response, request.getContextPath());
    }

    public void registerAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(request.getUserPrincipal() != null){
            addFlash(
                request,
                "error",
                "Déconnectez-vous pour créer un nouveau compte"
            );
            redirect(response, request.getContextPath());
        } else if(request.getMethod().equals("POST")) {
            User user = new User();
            user.hydrate(request);

            Validator v = user.validate();

            try {
                if(v.getError("user") == null){
                    getRepository(request).findOneByLogin(user.getLogin());
                    v.addError("login", "Ce nom d'utilisateur est déjà pris");
                }
            } catch(Exception e){}

            if(v.getError("password") == null && !user.getPassword().equals(request.getParameter("password_bis")))
                v.addError("password_bis", "Les mots de passe ne correspondent pas");

            if(v.isValid()){
                getRepository(request).create(user);
                addFlash(
                    request,
                    "success",
                    "Bienvenue " + user.getFirstName() + ", votre compte a été créé. Vous pouvez vous connecter"
                );
                redirect(response, request.getContextPath() + "/security/login");
            } else {
                request.setAttribute("errors", v);
                addFlash(
                    request,
                    "error",
                    "Le formulaire contient des erreurs"
                );
                flushFlashBag(request);
                render("security:register", request, response, "Inscription");
            }
        } else
            render("security:register", request, response, "Inscription");
    }

    public void logoutAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        request.logout();
        redirect(response, request.getContextPath());
    }



    private UserRepository getRepository(HttpServletRequest request) throws Exception
    {
        return ((UserRepository) getManager(request).getRepository("User"));
    }
}