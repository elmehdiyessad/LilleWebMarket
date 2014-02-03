package src.entity;


import java.util.Date;

import framework.Entity;
import framework.Validator;



enum UserStockState {
    BUY,
    SELL
}

public class UserStock extends Entity
{
    protected int stockId;
    protected String login;
    protected int marketId;
    protected int nbStock;
    protected int nbSold;
    protected int price;
    protected boolean assertion;
    protected Date creationDate;
    protected UserStockState buyOrSell;



    public UserStock()
    {
        setCreationDate(new Date());
        setBuyOrSell("BUY");
    }



    public Validator validate()
    {
        Validator v = new Validator();

        if(nbStock <= 0)
            v.addError("nb_stock", "Ce nombre doit être positif");

        if(price <= 0 || price >= 100)
            v.addError("price", "Le prix doit être positif et strictement inférieur à 100");

        return v;
    }



    public Integer getStockId()
    {
        return this.stockId;
    }

    public String getLogin()
    {
        return this.login;
    }

    public Integer getMarketId()
    {
        return this.marketId;
    }

    public Integer getNbStock()
    {
        return this.nbStock;
    }

    public Integer getNbSold()
    {
        return this.nbSold;
    }

    public Integer getPrice()
    {
        return this.price;
    }

    public Boolean getAssertion()
    {
        return this.assertion;
    }

    public Date getCreationDate()
    {
        return this.creationDate;
    }

    public String getBuyOrSell()
    {
        return this.buyOrSell.name();
    }

    public void setStockId(Integer stockId)
    {
        this.stockId = stockId;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setMarketId(Integer marketId)
    {
        this.marketId = marketId;
    }

    public void setNbStock(Integer nbStock)
    {
        this.nbStock = nbStock;
    }

    public void setNbSold(Integer nbSold)
    {
        this.nbSold = nbSold;
    }

    public void addNbSold(Integer nbSoldToAdd)
    {
        this.nbSold += nbSoldToAdd;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public void setAssertion(Boolean assertion)
    {
        this.assertion = assertion;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public void setBuyOrSell(String buyOrSell)
    {
        this.buyOrSell = UserStockState.valueOf(buyOrSell.trim().toUpperCase());
    }
}