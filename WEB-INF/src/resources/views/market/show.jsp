<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>



<ul class="actions">
    <li><a href="<%= request.getContextPath() %>/market/show?id=${param.id}${empty param.rev ? "&rev=true" : ""}">Marché inverse</a></li>
    <li><a href="<%= request.getContextPath() %>/market/show?id=${param.id}${empty param.rev ? "" : "&rev=true"}">Mettre à jour</a></li>
</ul>

<table>
    <caption>Ventes</caption>
    <thead>
        <th>Nombre</th>
        <th>Prix</th>
        <th>Utilisateur</th>
    </thead>
    <tbody>
        <c:forEach items="${market.stocks}" var="s">
            <c:if test="${s.assertion == !empty param.rev}">
                <tr>
                    <td class="center">
                        ${s.nbStock - s.nbSold}
                    </td>
                    <td class="center">
                        ${s.price} €
                    </td>
                    <td class="center">
                        ${s.login}
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>

<table>
    <caption>Achats</caption>
    <thead>
        <th>Nombre</th>
        <th>Prix</th>
        <th>Utilisateur</th>
    </thead>
    <tbody>
        <c:forEach items="${market.stocks}" var="s">
            <c:if test="${s.assertion == empty param.rev}">
                <tr>
                    <td class="center">
                        ${s.nbStock - s.nbSold}
                    </td>
                    <td class="center">
                        ${s.price} €
                    </td>
                    <td class="center">
                        ${s.login}
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>


<c:if test="${!empty pageContext.request.userPrincipal}">
    <div>
        <form action="<%= request.getContextPath() %>/market/buy?id=${market.marketId}${empty param.rev ? "" : "&rev=true"}" method="post">
            <h3>Acheter des titres</h3>

            <f:field type="number" label="Quantité" name="nb_stock" required="true"/>
            <f:field type="number" label="Prix" name="price" required="true"/>

            <p>
                <input type="submit" value="Acheter">
            </p>
        </form>
    </div>
</c:if>