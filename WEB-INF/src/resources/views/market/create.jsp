<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>



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
        <input type="datetime" name="term" id="term"
               placeholder="yyyy-mm-dd hh:mm:ss"
               pattern="([0-2][0-9]{3})\-([0-1][0-9])\-([0-3][0-9]) ([0-5][0-9])\:([0-5][0-9])\:([0-5][0-9])"
        >
    </p>

    <p class="submit">
        <input type="submit" value="Envoyer">
    </p>
</form>