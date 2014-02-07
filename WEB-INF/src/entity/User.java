package src.entity;


import framework.Entity;
import framework.Validator;



public class User extends Entity
{
    protected String login;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected int    cash;
    protected String role;



    public User(){}



    public Validator validate()
    {
        Validator v = new Validator();

        if(Validator.isBlank(login))
            v.addError("login", "Ce champ est obligatoire");
        else if(!Validator.hasMinLength(login, 4))
            v.addError("login", "Le nom d'utilisateur doit contenir au moins 4 caractères");

        if(Validator.isBlank(password))
            v.addError("password", "Ce champ est obligatoire");
        else if(!Validator.hasMinLength(password, 4) || !Validator.hasMaxLength(password, 45))
            v.addError("password", "Le mot de passe doit contenir de 4 à 45 caractères");

        if(Validator.isBlank(firstName))
            v.addError("firstName", "Ce champ est obligatoire");

        if(Validator.isBlank(lastName))
            v.addError("lastName", "Ce champ est obligatoire");

        return v;
    }



    public String getLogin()
    {
        return this.login;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getRole()
    {
        return this.role;
    }

    public Integer getCash()
    {
        return this.cash;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public void setCash(Integer cash)
    {
        this.cash = cash;
    }



    public String toString()
    {
        return firstName + " " + lastName;
    }
}