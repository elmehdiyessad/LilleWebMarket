package src.controller;


import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;

import src.entity.Market;



public class HomeController extends Controller
{
    public void indexAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        request.setAttribute("title", "Accueil");
    	request.setAttribute("markets", new ArrayList<Market>());

        render("home:index", request, response);
    }
}