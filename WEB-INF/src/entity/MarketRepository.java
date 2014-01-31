package src.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import framework.Repository;



public class MarketRepository extends Repository<Market>
{
    public MarketRepository(){}



    public Market findOneById(Integer id) throws Exception
    {
        PreparedStatement ps = prepareStatement(
            "SELECT * " +
            "FROM " + getTableName() + " AS m " +
            "LEFT JOIN " + getTableName("UserStock") + " AS us ON m.id = us.id_market " +
            "WHERE m.id = ?"
        );

        ps.setInt(1, id);

        return getSingleResult(ps.executeQuery());
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