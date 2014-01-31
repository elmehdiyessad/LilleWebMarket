<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="market" scope="request" class="src.entity.Market"/>

<ul class="actions">
    <li><a href="<%= request.getContextPath() %>/market/show?id=${param.id}${empty param.rev ? "&rev=true" : ""}">Marché inverse</a></li>
    <li><a href="<%= request.getContextPath() %>/market/show?id=${param.id}${empty param.rev ? "" : "&rev=true"}">Mettre à jour</a></li>
</ul>

<table>
    <caption>Achats</caption>
    <thead>
        <th>Nombre</th>
        <th>Prix</th>
        <th>Utilisateur</th>
    </thead>
    <tbody>
        <c:forEach items="${stockBuy}" var="s">
            <tr>
                <td class="center">
                    ${s.nbStock - s.nbSold}
                </td>
                <td class="center">
                    ${s.price} €
                </td>
                <td>
                    ${s.user}
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<table>
    <caption>Ventes</caption>
    <thead>
        <th>Nombre</th>
        <th>Prix</th>
        <th>Utilisateur</th>
    </thead>
    <tbody>
        <c:forEach items="${stockSell}" var="s">
            <tr>
                <td class="center">
                    ${s.nbStock - s.nbSold}
                </td>
                <td class="center">
                    ${s.price} €
                </td>
                <td>
                    ${s.user}
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>