package framework;

import java.io.IOException;
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
    private DataSource dataSource = null;
    private Connection connection = null;



    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            String[] path = request.getServletPath().split("/");
            String action = (path.length == 3 && path[2].length() > 0)
                ? path[2]
                : "index"
            ;

            Method method = this.getClass().getMethod(action + "Action", HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);

            if(connection != null)
                connection.close();
            
            connection = null;
        } catch(Exception e){
            debug(e, response);
        }
    }



    protected void render(String view, HttpServletRequest request, HttpServletResponse response)
    {
        try {
            getServletContext()
                .getRequestDispatcher("/WEB-INF/src/resources/views/" + view.replace(":", "/") + ".jsp")
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



    protected DataSource getDataSource()
    {
        if(dataSource != null)
            return dataSource;

        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context) initContext.lookup("java:/comp/env");
            dataSource          = (DataSource) envContext.lookup("database");
            return dataSource;
        } catch(NamingException e){}

        return null;
    }

    protected Connection getConnection()
    {
        if(connection != null)
            return connection;

        try {
            if(getDataSource() != null)
                connection = getDataSource().getConnection();

            return connection;
        } catch(SQLException e){}

        return null;
    }

    protected ResultSet executeQuery(String query)
    {
        try {
            return getConnection().createStatement().executeQuery(query);
        } catch(SQLException e){}

        return null;
    }



    private void debug(Exception e, HttpServletResponse response){
        try {
            response.getWriter().println(e.getMessage());
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}