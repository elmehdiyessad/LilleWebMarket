package src.entity;


import java.util.Date;

import framework.Entity;



enum UserStockState {
    BUY,
    SELL
}

public class UserStock extends Entity
{
    protected String login;
    protected int marketId;
    protected int nbStock;
    protected int nbSold;
    protected int price;
    protected boolean assertion;
    protected Date creationDate;
    protected UserStockState buyOrSell;



    public UserStock(){
        setCreationDate(new Date());
        setBuyOrSell("BUY");
    }



    public String getLogin() {
        return this.login;
    }

    public int getMarketId() {
        return this.marketId;
    }

    public int getNbStock() {
        return this.nbStock;
    }

    public int getNbSold() {
        return this.nbSold;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean getAssertion() {
        return this.assertion;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getBuyOrSell() {
        return this.buyOrSell.name();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public void setNbStock(int nbStock) {
        this.nbStock = nbStock;
    }

    public void setNbSold(int nbSold) {
        this.nbSold = nbSold;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAssertion(boolean assertion) {
        this.assertion = assertion;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = UserStockState.valueOf(buyOrSell.trim().toUpperCase());
    }
}