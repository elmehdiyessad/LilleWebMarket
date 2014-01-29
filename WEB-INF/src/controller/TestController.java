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



public class TestController extends HttpServlet
{
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    ResultSet resultSet = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        try {
            try {
                Context initContext  = new InitialContext();
                Context envContext  = (Context)initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("database");
                out.println("Good...");
            } catch(NamingException ex){
                out.println("NamingException");
                out.println(ex.getMessage());
            }

            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM lwm_utilisateur";
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
                System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4));

            out.println("All it's ok ?");

            connection.close();
        } catch(SQLException e){
            out.println("SQLException");
            out.println(e.getMessage());
        }

        out.println("OKOK");
    }
}