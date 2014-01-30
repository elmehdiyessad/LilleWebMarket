package framework;


import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;



public class EntityManager
{
    private static volatile EntityManager manager = null;
    private Connection connection;



    private EntityManager(Connection connection)
    {
        this.connection = connection;
    }



    public Repository getRepository(String repositoryName) throws Exception
    {
        Class<?> repositoryClass = Class.forName("src.entity." + repositoryName + "Repository");
        Constructor<?> ctor      = repositoryClass.getConstructor();
        Repository<?> repository = (Repository<?>) ctor.newInstance();
        repository.setConnection(connection);
        return repository;
    }



    public final static EntityManager getInstance(Connection connection)
    {
        if(EntityManager.manager == null) {
            synchronized(EntityManager.class) {
                if(EntityManager.manager == null) {
                    EntityManager.manager = new EntityManager(connection);
                }
            }
        }

        return EntityManager.manager;
    }
}