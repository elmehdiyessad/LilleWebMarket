<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <c:forEach items="${market.stocks}" var="s">
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
        <c:forEach items="${market.stocks}" var="s">
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


<c:if test="${!empty pageContext.request.userPrincipal}">
    <div>
        <form action="<%= request.getContextPath() %>/market/buy?id=${market.id}" method="post">
            <h3>Acheter des titres</h3>
            <p>
                <label for="nb_stock">Quantité</label>
                <input type="number" name="nb_stock" id="nb_stock">
            </p>
            <p>
                <label for="price">Prix</label>
                <input type="number" name="price" id="price">
            </p>
            <p>
                <input type="submit" value="Acheter">
            </p>
        </form>
    </div>
</c:if>