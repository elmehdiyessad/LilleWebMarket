package src.entity;


import java.util.Date;

import framework.Entity;



public class Marche extends Entity
{
    protected int    id;
    protected String titre;
    protected String titreInv;
    protected Date   echeance;



    public Marche(){}



    public int getId() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public String getTitreInv() {
        return this.titreInv;
    }

    public Date getEcheance() {
        return this.echeance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTitreInv(String titreInv) {
        this.titreInv = titreInv;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }



    public String toString()
    {
        return titre + " (jusqu'au " + echeance + ")";
    }
}