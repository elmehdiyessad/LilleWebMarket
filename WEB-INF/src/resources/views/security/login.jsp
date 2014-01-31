<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<form action="<%= response.encodeURL("j_security_check") %>" method="post">
    <p>
        <label for="login">Nom d'utilisateur</label>
        <input type="text" name="j_username" id="login">
    </p>
    <p>
        <label for="password">Mot de passe</label>
        <input type="password" name="j_password" id="password">
    </p>

    <p class="submit">
        <input type="submit" value="Connexion">
    </p>
</form>