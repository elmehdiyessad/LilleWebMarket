<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<div class="box">
    <form action="<%= request.getContextPath() %>/market/create" method="post" class="box-in">
        <f:field label="Titre" name="title"/>
        <f:field label="Titre inverse" name="title_rev"/>
        <f:field label="Date d'échéance" name="term" type="datetime"
                 placeholder="yyyy-mm-dd hh:mm:ss"
                 pattern="([0-2][0-9]{3})\-([0-1][0-9])\-([0-3][0-9]) ([0-5][0-9])\:([0-5][0-9])\:([0-5][0-9])"/>

        <p class="submit">
            <button type="submit">Envoyer</button>
        </p>
    </form>
</div>