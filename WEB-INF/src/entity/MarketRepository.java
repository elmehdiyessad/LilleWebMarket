package src.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import framework.Repository;



public class MarketRepository extends Repository<Market>
{
    public MarketRepository(){}



    @Override
    public String getQuery() throws Exception
    {
        return
            "SELECT *, (" + getVariationQuery() + ") AS variation " +
            "FROM " + getTableName() + " AS m " +
            "WHERE term > NOW()"
        ;
    }

    private String getVariationQuery() throws Exception
    {
        return
            "SELECT (" +
                "SELECT TRUNC(SUM(price * quantity)/SUM(quantity)) " +
                "FROM " + getTableName("VariationsMarket") + " AS vm " +
                "WHERE instant >= (NOW() - INTERVAL '1 hour') " +
                  "AND m.market_id = vm.market_id " +
            ") - (" +
                "SELECT TRUNC(SUM(price * quantity)/SUM(quantity)) " +
                "FROM " + getTableName("VariationsMarket") + " AS vm " +
                "WHERE instant >= (NOW() - INTERVAL '2 hour') " +
                  "AND instant < (NOW() - INTERVAL '1 hour') " +
                  "AND m.market_id = vm.market_id " +
            ")";
    }



    /**
     * Recherche un marché
     * Récupère tous les titres associés au marché
     *
     * @param id Id du marché
     * @return Marché ayant l'id demandé
     */
    public Market findOneById(int id) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "SELECT * " +
            "FROM " + getTableName() + " AS m " +
            "LEFT JOIN " + getTableName("UserStock") + " AS us " +
                "ON m.market_id = us.market_id " +
                  "AND us.nb_stock - us.nb_sold > 0" +
            "WHERE m.market_id = ? " +
            "ORDER BY us.price ASC, us.creation ASC"
        );

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Market m     = getSingleResult(rs);
        UserStock us;

        do {
            if(rs.getInt("stock_id") == 0)
                break;

            us = new UserStock();
            us.hydrate(rs);
            m.getStocks().add(us);
        } while(rs.next());

        return m;
    }



    /**
     * Crée un nouveau marché
     *
     * @param m Le marché à créer
     * @return Id du marché créé
     */
    public Object create(Market m) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "INSERT INTO " + getTableName() + " (title, title_rev, term, maker)" +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING market_id"
        );

        ps.setString(1, m.getTitle());
        ps.setString(2, m.getTitleRev());
        ps.setDate(3, new java.sql.Date(m.getTerm().getTime()));
        ps.setString(4, m.getMaker());

        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getInt(1);
    }



    public Map<Integer, Integer> getVariationsById(int id) throws Exception
    {
        Map<Integer, Integer> results = new HashMap<Integer, Integer>();

        PreparedStatement ps = prepareStatement(
            "SELECT TRUNC(SUM(price * quantity)/SUM(quantity)) AS variation, EXTRACT(HOUR FROM instant) AS hour " +
            "FROM lwm_variations_market " +
            "WHERE instant >= (NOW() - INTERVAL '1 day') " +
                "AND market_id = ? " +
            "GROUP BY hour"
        );

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        while(rs.next())
            results.put(rs.getInt("hour"), rs.getInt("variation"));

        return results;
    }


    public void addVariation(int marketId, int price, int quantity) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "INSERT INTO lwm_variations_market (market_id, price, quantity) " +
            "VALUES (?, ?, ?)"
        );

        ps.setInt(1, marketId);
        ps.setInt(2, price);
        ps.setInt(3, quantity);

        ps.execute();
    }
}