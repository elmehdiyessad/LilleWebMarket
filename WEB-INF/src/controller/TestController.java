package src.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;

import src.entity.Utilisateur;



public class TestController extends Controller
{
    public void indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();

        try {
            ArrayList<Utilisateur> utilisateurs = getManager().getRepository("Utilisateur").findAll();
            for(Utilisateur utilisateur : utilisateurs)
                out.println(utilisateur);
        } catch(Exception e){
            debug(e, response);
        }
    }

    public void totoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.println("TOOTOOO");
    }
}