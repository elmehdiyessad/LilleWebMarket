package src.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;



public class HomeController extends Controller
{
    public void indexAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        request.setAttribute("markets", getManager(request).getRepository("Market").findAll());

        render("home:index", request, response, "Accueil");
    }
}