<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<h2>Connexion</h2>

<form action="<%= response.encodeURL("j_security_check") %>" method="post">
    <p>
        <label for="name">Nom d'utilisateur</label>
        <input type="text" name="j_username">
    </p>
    <p>
        <label for="e-mail">Mot de passe</label>
        <input type="password" name="j_password">
    </p>

    <p class="submit">
        <input type="submit" value="Submit">
    </p>
</form>