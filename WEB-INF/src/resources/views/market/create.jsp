<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>



<h2>Créer un marché</h2>

<form action="<%= request.getContextPath() %>/market/create" method="post">
    <p>
        <label for="title">Titre</label>
        <input type="text" name="title" id="title">
    </p>
    <p>
        <label for="title_rev">Titre inverse</label>
        <input type="text" name="title_rev" id="title_rev">
    </p>
    <p>
        <label for="term">Date d'échéance</label>
        <input type="datetime" name="term" id="term">
    </p>

    <p class="submit">
        <input type="submit" value="Envoyer">
    </p>
</form>