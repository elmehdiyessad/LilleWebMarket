package framework;


import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



public abstract class Repository<T extends Entity>
{
    private Connection connection;



    public abstract Object create(T entity) throws Exception;

    public String getQuery() throws Exception
    {
        return
            "SELECT * " +
            "FROM " + getTableName()
        ;
    }

    public List<T> findAll() throws Exception
    {
        ResultSet rs = getConnection().createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        ).executeQuery(
            getQuery()
        );

        return resultSetToList(rs);
    }

    public T getSingleResult(ResultSet rs) throws Exception
    {
        rs.beforeFirst();
        if(!rs.next())
            throw new Exception("Aucun résultat");

        T entity = createEntity(rs);

        return entity;
    }



    protected List<T> resultSetToList(ResultSet rs) throws Exception
    {
        List<T> results = new ArrayList<T>();
        rs.beforeFirst();

        while(rs.next())
            results.add(createEntity(rs));

        return results;
    }



    protected String getEntityName() throws Exception
    {
        return this.getClass().getSimpleName().replace("Repository", "");
    }

    protected String getTableName(String name) throws Exception
    {
        return "lwm_" + camelCaseToUnderscore(name);
    }
    protected String getTableName() throws Exception
    {
        return getTableName(getEntityName());
    }

    protected String camelCaseToUnderscore(String s)
    {
        return s.replaceAll("(.)([A-Z])", "$1_$2").toLowerCase();
    }

    protected T createEntity(ResultSet rs) throws Exception
    {
        Class<?> entityClass = Class.forName("src.entity." + getEntityName());
        Constructor<?> ctor  = entityClass.getConstructor();
        T entity             = (T) ctor.newInstance();

        entity.hydrate(rs);

        return entity;
    }



    protected Connection getConnection()
    {
        return connection;
    }

    protected PreparedStatement prepareStatement(String query) throws Exception
    {
        return getConnection().prepareStatement(
            query,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );
    }

    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }
}