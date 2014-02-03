<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>



<ul class="actions">
    <li><a href="<%= request.getContextPath() %>/market/show?id=${param.id}${empty param.rev ? "" : "&rev=true"}">Actualiser</a></li>
    <li><a href="<%= request.getContextPath() %>/market/show?id=${param.id}${empty param.rev ? "&rev=true" : ""}">Marché inverse</a></li>
</ul>

<div class="row">
    <div class="box w50">
        <div class="box-in">
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
        </div>
    </div>
    <div class="box w50">
        <div class="box-in">
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
        </div>
    </div>
</div>

<c:if test="${!empty pageContext.request.userPrincipal}">
    <div class="row">
        <div class="box w50">
            <form action="<%= request.getContextPath() %>/market/buy?id=${market.marketId}${empty param.rev ? "" : "&rev=true"}" method="post" class="box-in">
                <h3>Acheter des titres</h3>

                <f:field type="number" label="Quantité" name="nb_stock" required="true" min="1"/>
                <f:field type="number" label="Prix" name="price" required="true" min="1" max="99"/>

                <p class="submit">
                    <button type="submit">Acheter</button>
                </p>
            </form>
        </div>
    </div>
</c:if>