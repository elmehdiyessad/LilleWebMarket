<%@ page contentType="text/html" pageEncoding="UTF-8"%>



<ul class="actions">
    <li><a href="<%= request.getContextPath() %>/security/register">Créer un compte</a></li>
</ul>

<form action="<%= response.encodeURL("j_security_check") %>" method="post">
    <f:field label="Nom d'utilisateur" name="j_username" required="true"/>
    <f:field label="Mot de passe" name="j_password" required="true" type="password"/>

    <p class="submit">
        <input type="submit" value="Connexion">
    </p>
</form>