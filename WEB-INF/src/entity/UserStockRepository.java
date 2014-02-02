package src.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import framework.Repository;



public class UserStockRepository extends Repository<UserStock>
{
    public UserStockRepository(){}



    /**
     * Recherche les titres achetables par ordre de priorité
     *
     * @param marketId Id du marché concerné
     * @param assertion Défini si on achète pour le marché ou son symétrique
     * @param price Prix maximal du titre
     * @return Liste des titres achetables par rapport aux conditions
     */
    public ArrayList<UserStock> findPurchasable(int marketId, boolean assertion, int price, String login) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "SELECT * " +
            "FROM " + getTableName() + " AS us " +
            "WHERE market_id = ? " +
              "AND us.login != ? " +
              "AND us.assertion = ? " +
              "AND us.price <= ? " +
              "AND us.nb_stock - us.nb_sold > 0 " +
            "ORDER BY us.price ASC, us.creation ASC"
        );

        ps.setInt(1, marketId);
        ps.setString(2, login);
        ps.setBoolean(3, !assertion);
        ps.setInt(4, price);

        return resultSetToList(ps.executeQuery());
    }



    /**
     * Met relativement à jour le nombre de titres vendus
     *
     * @param stockId Id du titre concerné
     * @param cash Valeur de la variation du nombre de titres vendus
     */
    public void addToNbSold(int stockId, int nbSoldVariation) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "UPDATE " + getTableName() + " " +
            "SET nb_sold = nb_sold " + (nbSoldVariation > 0 ? "+" : "-") + " ? " +
            "WHERE stock_id = ?"
        );

        ps.setInt(1, Math.abs(nbSoldVariation));
        ps.setInt(2, stockId);

        ps.execute();
    }


    /**
     * Crée un nouveau titre
     *
     * @param us Le titre à créer
     */
    public void create(UserStock us) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "INSERT INTO " + getTableName() + " (login, market_id, nb_stock, nb_sold, price, assertion, buy_or_sell)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?::etat) "
        );

        ps.setString(1, us.getLogin());
        ps.setInt(2, us.getMarketId());
        ps.setInt(3, us.getNbStock());
        ps.setInt(4, us.getNbSold());
        ps.setInt(5, us.getPrice());
        ps.setBoolean(6, us.getAssertion());
        ps.setString(7, us.getBuyOrSell());

        ps.execute();
    }
}