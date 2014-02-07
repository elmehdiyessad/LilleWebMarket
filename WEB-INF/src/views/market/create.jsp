<%@ page contentType="text/html" pageEncoding="UTF-8"%>


<div class="box">
    <form action="${contextPath}/market/create" method="post" class="box-in">
        <f:field label="Titre" name="title"/>
        <f:field label="Titre inverse" name="title_rev"/>
        <f:field label="Date d'échéance" name="term" type="date"
                 placeholder="yyyy-mm-dd"
                 pattern="([0-2][0-9]{3})\-([0-1][0-9])\-([0-3][0-9])"/>

        <p class="submit">
            <button type="submit">Envoyer</button>
        </p>
    </form>
</div>