package src.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import framework.Repository;



public class UserRepository extends Repository<User>
{
    public UserRepository(){}



    /**
     * Recherche un utilisateur
     *
     * @param login Nom d'utilisateur
     * @return Utilisateur ayant le login indiqué
     */
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

    /**
     * Met relativement à jour le cash d'un utilisateur
     *
     * @param login Nom d'utilisateur
     * @param cash Valeur de la variation du cash
     */
    public void addToCash(String login, int cash) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "UPDATE " + getTableName("UserInfos") + " " +
            "SET cash = cash " + (cash > 0 ? "+" : "-") + " ? " +
            "WHERE login = ?"
        );

        ps.setInt(1, Math.abs(cash));
        ps.setString(2, login);

        ps.execute();
    }
}