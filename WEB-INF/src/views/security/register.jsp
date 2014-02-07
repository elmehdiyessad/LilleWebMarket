<%@ page contentType="text/html" pageEncoding="UTF-8"%>



<div class="box">
    <form method="post" class="box-in">
        <f:field label="Nom d'utilisateur" name="login" required="true" autofocus=""/>
        <f:field label="Nom" name="last_name" required="true"/>
        <f:field label="Prénom" name="first_name" required="true"/>
        <f:field label="Mot de passe" name="password" required="true" type="password"/>
        <f:field label="Retapez le mot de passe" name="password_bis" required="true" type="password"/>

        <p class="submit">
            <button type="submit">Inscription</button>
        </p>
    </form>
</div>