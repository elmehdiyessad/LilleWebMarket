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

    /**
     * Crée un nouvel utilisateur
     *
     * @param u L'utilisateur à créer
     * @return Login de l'utilisateur créé
     */
    public Object create(User u) throws Exception
    {
        /*
        PreparedStatement ps = prepareStatement(
            "INSERT INTO " + getTableName() + " (login, market_id, nb_stock, nb_sold, price, assertion, buy_or_sell)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?::ETAT) " +
            "RETURNING login"
        );

        ps.setString(1, us.getLogin());
        ps.setInt(2, us.getMarketId());
        ps.setInt(3, us.getNbStock());
        ps.setInt(4, us.getNbSold());
        ps.setInt(5, us.getPrice());
        ps.setBoolean(6, us.getAssertion());
        ps.setString(7, us.getBuyOrSell());

        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getString(1);
        */
        return null;
    }
}