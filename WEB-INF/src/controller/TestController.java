package src.controller;

import java.io.IOException;
import java.sql.Connection;
import java.io.PrintWriter;
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



public class TestController extends Controller
{
    ResultSet resultSet = null;

    public void indexAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.println(request.getContextPath());
        out.println(request.getServletPath());

        try {
            resultSet = executeQuery("SELECT * FROM lwm_utilisateur");
            while (resultSet.next())
                out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4));

            out.println("All it's ok ?");
        } catch(SQLException e){
            out.println("SQLException");
            out.println(e.getMessage());
        }

        out.println("OKOK");
    }

    public void totoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.println("TOOTOOO");
    }
}