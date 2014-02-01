package src.entity;


import framework.Entity;



public class User extends Entity
{
    protected String login;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected int    cash;



    public User(){}



    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getCash() {
        return this.cash;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void addCash(int cashToAdd)
    {
        this.cash += cashToAdd;
    }

    public void subCash(int cashToSub)
    {
        this.cash -= cashToSub;
    }



    public String toString()
    {
        return firstName + " " + lastName;
    }
}