package src.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import framework.Entity;
import framework.Validator;



public class Market extends Entity
{
    protected int       marketId;
    protected String    title;
    protected String    titleRev;
    protected Date      term;
    protected String    maker;
    protected int       variation;
    protected boolean   enabled;
    protected boolean   response;
    protected List<UserStock> stocks;



    public Market()
    {
        stocks = new ArrayList<UserStock>();
    }



    public Validator validate()
    {
        Validator v = new Validator();

        if(Validator.isBlank(title))
            v.addError("title", "Ce champ est obligatoire");
        else if(!Validator.hasMinLength(title, 5))
            v.addError("title", "Ce titre est trop court");

        if(Validator.isBlank(titleRev))
            v.addError("titleRev", "Ce champ est obligatoire");
        else if(!Validator.hasMinLength(titleRev, 5))
            v.addError("titleRev", "Ce titre est trop court");

        if(term == null)
            v.addError("term", "La date renseignée est invalide");
        else if(term.before(new Date()))
            v.addError("term", "L'échéance doit être future à aujourd'hui");

        return v;
    }



    public Integer getMarketId()
    {
        return this.marketId;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getTitleRev()
    {
        return this.titleRev;
    }

    public Date getTerm()
    {
        return this.term;
    }

    public String getMaker()
    {
        return this.maker;
    }

    public Integer getVariation()
    {
        return this.variation;
    }

    public boolean getEnabled()
    {
        return this.enabled;
    }

    public boolean getResponse()
    {
        return this.response;
    }

    public List<UserStock> getStocks()
    {
        return this.stocks;
    }

    public void setMarketId(Integer marketId)
    {
        this.marketId = marketId;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setTitleRev(String titleRev)
    {
        this.titleRev = titleRev;
    }

    public void setTerm(Date term)
    {
        this.term = term;
    }

    public void setMaker(String maker)
    {
        this.maker = maker;
    }

    public void setVariation(Double variation)
    {
        this.variation = variation.intValue();
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public void setResponse(Boolean response)
    {
        this.response = response;
    }

    public void setStocks(List<UserStock> stocks)
    {
        this.stocks = stocks;
    }



    public String toString()
    {
        return title + " (jusqu'au " + term + ")";
    }
}