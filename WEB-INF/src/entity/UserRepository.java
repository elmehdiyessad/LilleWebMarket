package src.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import framework.Repository;



public class UserRepository extends Repository<User>
{
    public UserRepository(){}



    public User findOneByLogin(String login) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "SELECT * " +
            "FROM " + getTableName() + " " +
            "WHERE login = ?"
        );

        ps.setString(1, login);

        return getSingleResult(ps.executeQuery());
    }
}