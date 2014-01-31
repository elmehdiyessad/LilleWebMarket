package src.entity;


import java.util.Date;

import framework.Entity;



public class UserStock extends Entity
{
    protected int    id;
    protected String title;
    protected String titleRev;
    protected Date   term;



    public UserStock(){}



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



    public String toString()
    {
        return title + " (jusqu'au " + term + ")";
    }
}