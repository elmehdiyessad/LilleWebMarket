package framework;

import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
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



public abstract class Controller extends HttpServlet
{
    private Connection connection = null;



    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();

        try {
            if(request.getUserPrincipal() != null)
                request.setAttribute(
                    "user",
                    getManager().getRepository("Utilisateur").findOneBy("login", request.getUserPrincipal().getName())
                );

            String[] path = request.getServletPath().split("/");
            String action = (path.length == 3 && path[2].length() > 0)
                ? path[2]
                : "index"
            ;

            request.setAttribute("title", action.replace("([A-Z])", " $2"));

            Method method = this.getClass().getMethod(action + "Action", HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
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

    protected void redirect(HttpServletResponse response, String path)
    {
        try {
            response.sendRedirect(response.encodeURL(path));
        } catch(Exception e){
            debug(e, response);
        }
    }



    protected DataSource getDataSource() throws NamingException
    {
        Context initContext = new InitialContext();
        Context envContext  = (Context) initContext.lookup("java:/comp/env");
        return (DataSource) envContext.lookup("database");
    }

    protected Connection getConnection() throws Exception
    {
        if(connection != null && !connection.isClosed())
            return connection;

        connection = getDataSource().getConnection();

        return connection;
    }



    protected EntityManager getManager() throws Exception
    {
        return EntityManager.getInstance(getConnection());
    }



    protected void debug(Exception e, HttpServletResponse response){
        try {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            response.getWriter().println(errors.toString());
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}