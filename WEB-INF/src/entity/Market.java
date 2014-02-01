package src.entity;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import framework.Entity;



public class Market extends Entity
{
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected int    id;
    protected String title;
    protected String titleRev;
    protected Date   term;
    protected ArrayList<UserStock> stocks;



    public Market(){
        stocks = new ArrayList<UserStock>();
    }



    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleRev() {
        return this.titleRev;
    }

    public Date getTerm() {
        return this.term;
    }

    public ArrayList<UserStock> getStocks() {
        return this.stocks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleRev(String titleRev) {
        this.titleRev = titleRev;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public void setTerm(String term) throws Exception {
        this.term = formatter.parse(term);
    }

    public void setStocks(ArrayList<UserStock> stocks) {
        this.stocks = stocks;
    }



    public String toString()
    {
        return title + " (jusqu'au " + term + ")";
    }
}