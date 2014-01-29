package src.entity;


import framework.Entity;



public class Utilisateur extends Entity
{
    protected String login;
    protected String mdp;
    protected String nom;
    protected String prenom;
    protected int    cash;



    public Utilisateur(){}



    public String getLogin()
    {
        return this.login;
    }

    public String getMdp()
    {
        return this.mdp;
    }

    public String getNom()
    {
        return this.nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    public int getCash() {
        return this.cash;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setMdp(String mdp)
    {
        this.mdp = mdp;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }



    public String toString()
    {
        return prenom + " " + nom;
    }
}