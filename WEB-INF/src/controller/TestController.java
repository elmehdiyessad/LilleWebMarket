package src.controller;


import java.io.IOException;
import java.sql.Connection;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import framework.Controller;

import src.entity.Utilisateur;



public class TestController extends Controller
{
    ResultSet resultSet = null;

    public void indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();

        resultSet = executeQuery("SELECT * FROM lwm_utilisateur");

        try {
            while (resultSet.next()){
                Utilisateur u = new Utilisateur();
                u.hydrate(resultSet, out);
                out.println(u);
            }
        } catch(Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));

            out.println("Hydrate fail");
            out.println(errors.toString());
        }
    }

    public void totoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.println("TOOTOOO");
    }
}