package src.entity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import framework.Repository;



public class UserStockRepository extends Repository<UserStock>
{
    public UserStockRepository(){}



    public ArrayList<UserStock> findPurchasable(int marketId, boolean assertion, int price) throws Exception
    {
        return null;
    }
}