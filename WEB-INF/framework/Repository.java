package framework;


import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



public abstract class Repository<T extends Entity>
{
    private Connection connection;



    public String getQuery(String condition) throws Exception
    {
        return 
            "SELECT * " +
            "FROM lwm_" + camelCaseToUnderscore(getEntityName()) + " " + 
            condition + ";"
        ;
    }

    public String getQuery() throws Exception
    {
        return getQuery("");
    }



    public T findOneBy(String field, String value) throws Exception
    {
        ResultSet rs = connection.createStatement().executeQuery(
            getQuery(
                "WHERE " + camelCaseToUnderscore(field) + " = '" + value + "'"
            )
        );

        rs.next();

        T entity = createEntity(rs);

        // Try to go to next to check it is single result
        if(!rs.next())
            return entity;

        throw new Exception("Not single result");
    }

    public ArrayList<T> findBy(String field, String value) throws Exception
    {
        ResultSet rs = connection.createStatement().executeQuery(
            getQuery(
                "WHERE " + camelCaseToUnderscore(field) + " = '" + value + "'"
            )
        );

        return resultSetToList(rs);
    }

    public ArrayList<T> findAll() throws Exception
    {
        ResultSet rs = connection.createStatement().executeQuery(
            getQuery()
        );

        return resultSetToList(rs);
    }



    protected ArrayList<T> resultSetToList(ResultSet rs) throws Exception
    {
        ArrayList<T> results = new ArrayList<T>();

        while(rs.next())
            results.add(createEntity(rs));

        return results;
    }



    protected String getEntityName() throws Exception
    {
        return this.getClass().getSimpleName().replace("Repository", "");
    }

    protected String camelCaseToUnderscore(String s)
    {
        return s.replace("(.)([A-Z])", "$1_$2").toLowerCase();
    }

    protected T createEntity(ResultSet rs) throws Exception
    {
        Class<?> entityClass = Class.forName("src.entity." + getEntityName());
        Constructor<?> ctor  = entityClass.getConstructor();
        T entity             = (T) ctor.newInstance();

        entity.hydrate(rs);

        return entity;
    }



    public Connection getConnection()
    {
        return connection;
    }

    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }
}