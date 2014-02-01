package framework;

import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import src.entity.User;
import src.entity.UserRepository;



public abstract class Controller extends HttpServlet
{
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out     = response.getWriter();
        HttpSession session = request.getSession(true);

        try {
            if(request.getUserPrincipal() != null)
                request.setAttribute(
                    "user",
                    ((UserRepository) getManager(request).getRepository("User")).findOneByLogin(request.getUserPrincipal().getName())
                );

            String[] path = request.getServletPath().split("/");
            String action = (path.length == 3 && path[2].length() > 0)
                ? path[2]
                : "index"
            ;

            request.setAttribute("title", action.replace("([A-Z])", " $2"));
            request.setAttribute("flashBag", (HashMap<String, String>) session.getAttribute("flashBag"));

            session.setAttribute("flashBag", new HashMap<String, String>());

            Method method = this.getClass().getMethod(action + "Action", HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);

            try {
                ((Connection) request.getAttribute("connection")).close();
            } catch(Exception e){}
        } catch(Exception e){
            debug(e, response);
        }
    }



    protected void render(String view, HttpServletRequest request, HttpServletResponse response)
    {
        request.setAttribute("template", "/WEB-INF/src/resources/views/" + view.replace(":", "/") + ".jsp");

        try {
            getServletContext()
                .getRequestDispatcher("/WEB-INF/src/resources/views/base.jsp")
                .forward(request, response);
        } catch(Exception e){
            debug(e, response);
        }
    }
    protected void render(String view, HttpServletRequest request, HttpServletResponse response, String title)
    {
        request.setAttribute("title", title);
        render(view, request, response);
    }

    protected void redirect(HttpServletResponse response, String path)
    {
        try {
            response.sendRedirect(response.encodeURL(path));
        } catch(Exception e){
            debug(e, response);
        }
    }



    protected void addFlash(HttpServletRequest request, String type, String message)
    {
        ((HashMap<String, String>) request.getSession(true).getAttribute("flashBag")).put(type, message);
    }



    protected DataSource getDataSource() throws NamingException
    {
        Context initContext = new InitialContext();
        Context envContext  = (Context) initContext.lookup("java:/comp/env");
        return (DataSource) envContext.lookup("database");
    }



    protected EntityManager getManager(HttpServletRequest request) throws Exception
    {
        Connection connection = (Connection) request.getAttribute("connection");
        if(connection == null || connection.isClosed()) {
            connection = getDataSource().getConnection();
            request.setAttribute("connection", connection);
        }

        return EntityManager.getInstance(connection);
    }



    protected User getUser(HttpServletRequest request)
    {
        return ((User) request.getAttribute("user"));
    }



    protected void debug(Exception e, HttpServletResponse response)
    {
        try {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            response.getWriter().println(errors.toString());
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}