package src.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import framework.Repository;



public class MarketRepository extends Repository<Market>
{
    public MarketRepository(){}



    public Market findOneById(String id) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "SELECT * " +
            "FROM " + getTableName() + " AS m " +
            "LEFT JOIN " + getTableName("UserStock") + " AS us ON m.id = us.market_id " +
            "WHERE m.id = ?"
        );

        ps.setInt(1, Integer.parseInt(id));

        ResultSet rs = ps.executeQuery();
        Market m     = getSingleResult(rs);
        UserStock us;

        while(rs.next()){
            us = new UserStock();
            us.hydrate(rs);
            m.getStocks().add(us);
        }

        return m;
    }

    public Integer create(Market m) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "INSERT INTO " + getTableName() + " (title, title_rev, term)" +
            "VALUES (?, ?, ?) " +
            "RETURNING id"
        );

        ps.setString(1, m.getTitle());
        ps.setString(2, m.getTitleRev());
        ps.setDate(3, new java.sql.Date(m.getTerm().getTime()));

        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getInt(1);
    }
}