<%@ page contentType="text/html" pageEncoding="UTF-8"%>



<ul class="actions">
    <li><a href="${contextPath}/security/register">Créer un compte</a></li>
</ul>

<div class="box">
    <form action="<%= response.encodeURL("j_security_check") %>" method="post" class="box-in">
        <f:field label="Nom d'utilisateur" name="j_username" required="true" autofocus=""/>
        <f:field label="Mot de passe" name="j_password" required="true" type="password"/>

        <p class="submit">
            <button type="submit">Connexion</button>
        </p>
    </form>
</div>