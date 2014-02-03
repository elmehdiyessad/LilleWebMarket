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
            "LEFT JOIN " + getTableName("UserInfos") + " AS ui "
                + "ON u.login = ui.login " +
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
        // Crée l'utilisateur
        PreparedStatement ps = prepareStatement(
            "INSERT INTO " + getTableName() + " (login, password, first_name, last_name)" +
            "VALUES (?, MD5(?), ?, ?) "
        );

        ps.setString(1, u.getLogin());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getFirstName());
        ps.setString(4, u.getLastName());

        ps.execute();


        // Crée l'entrée qui défini les infos de l'User
        ps = prepareStatement(
            "INSERT INTO " + getTableName("UserInfos") + " (login, role)" +
            "VALUES (?, ?) "
        );

        ps.setString(1, u.getLogin());
        ps.setString(2, "user");

        ps.execute();


        return u.getLogin();
    }
}