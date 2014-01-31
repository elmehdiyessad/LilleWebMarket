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
            "FROM " + getTableName() + " AS u " +
            "LEFT JOIN " + getTableName("UserInfos") + " AS ui ON u.login = ui.login " +
            "WHERE u.login = ?"
        );

        ps.setString(1, login);

        return getSingleResult(ps.executeQuery());
    }
}