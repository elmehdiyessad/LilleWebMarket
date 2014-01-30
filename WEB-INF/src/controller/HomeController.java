package src.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;

import src.entity.Marche;



public class HomeController extends Controller
{
    public void indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	request.setAttribute("marches", new ArrayList<Marche>());

        render("home:index", request, response);
    }
}