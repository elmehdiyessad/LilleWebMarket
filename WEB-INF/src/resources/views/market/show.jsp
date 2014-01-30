<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:useBean id="market" scope="request" class="src.entity.Market"/>

<h2>Marché : ${market.title}</h2>

<ul class="actions">
    <li><a href="<%= request.getContextPath() %>">Mettre à jour</a></li>
</ul>

<table>
    <thead>
        <th>Information</th>
        <th>Echéance</th>
        <th>Variation</th>
    </thead>
    <tfoot>
        <th>Information</th>
        <th>Echéance</th>
        <th>Variation</th>
    </tfoot>
    <tbody>
        <!-- TODO -->
    </tbody>
</table>