package src.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import framework.Repository;



public class MarketRepository extends Repository<Market>
{
    public MarketRepository(){}



    @Override
    public String getQuery() throws Exception
    {
        return
            "SELECT * " +
            "FROM " + getTableName() + " " +
            "WHERE term > NOW()"
        ;
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
}