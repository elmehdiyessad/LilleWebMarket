package src.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginController extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(request.getDispatcherType() == javax.servlet.DispatcherType.FORWARD)
            response.sendRedirect("/LilleWebMarket/login");
        else
            getServletContext().getRequestDispatcher("/WEB-INF/src/resources/views/login/login.jsp")
                               .forward(request, response);
    }
}