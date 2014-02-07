<%@ page contentType="text/html" pageEncoding="UTF-8"%>



<ul class="actions">
    <li><a href="${contextPath}/market/show?id=${param.id}${empty param.rev ? "&rev=true" : ""}">Marché inverse</a></li>
</ul>

<c:if test="${!empty chartData or user.login == market.maker or market.term < now}">
    <div class="row">
        <c:if test="${!empty chartData}">
            <div class="box ${user.login == market.maker ? "w50" : ""}">
                <div class="box-in">
                    <h3>Variations sur 24h</h3>
                    <ul class="actions">
                        <li><span class="variation ${market.variation < 0 ? "negative" : "positive"}">${market.variation < 0 ? "-" : "+"} ${m:abs(market.variation)} %</span></li>
                        <li><a href="${contextPath}/market/show?id=${param.id}${empty param.rev ? "" : "&rev=true"}">Actualiser</a></li>
                    </ul>
                </div>
                <div class="chart" data-chart="[${chartData}]"></div>
            </div>
        </c:if>

        <c:if test="${user.login == market.maker}">
            <div class="box ${!empty chartData ? "w50" : ""}">
                <div class="box-in">
                    <h3>Gestion du marché</h3>

                    <p>
                        Vous êtes l'initiateur de ce marché qui ${market.term > now ? "prendra" : "a pris"} fin le <fmt:formatDate value="${market.term}" pattern="dd MMMM yyyy" />
                    </p>
                    <c:if test="${market.term < now}">
                        <form action="${contextPath}/market/end?id=${market.marketId}" method="post">
                            <p>
                                Le marché est <strong>terminé</strong>. Vous devez donc saisir si oui ou non l'événement suivant s'est produit : <br>
                                <blockquote>"${market.title}"</blockquote>
                            </p>

                            <p class="submit">
                                <button type="submit" name="no">Non</button>
                                <button type="submit" name="yes">Oui</button>
                            </p>
                        </form>
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${market.term < now and user.login != market.maker}">
            <div class="box ${!empty chartData ? "w50" : ""}">
                <div class="box-in">
                    <h3>Marché en attente de réponse</h3>

                    <c:if test="${!empty user}">
                        <ul class="actions">
                            <li><span>${nbStock > 0 ? nbStock : 0}</span></li>
                        </ul>
                    </c:if>

                    <p>
                        Le marché est en attente d'une réponse de son créateur.
                    </p>
                </div>
            </div>
        </c:if>
    </div>
</c:if>

<c:if test="${market.term > now}">
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

    <c:if test="${!empty user}">
        <div class="row">
            <div class="box w50">
                <form action="${contextPath}/market/buy?id=${market.marketId}${empty param.rev ? "" : "&rev=true"}" method="post" class="box-in">
                    <h3>Acheter des titres</h3>

                    <f:field type="number" label="Quantité" name="nb_stock" required="true" min="1"/>
                    <f:field type="number" label="Prix" name="price" required="true" min="1" max="99"/>

                    <p class="submit">
                        <button type="submit">Acheter</button>
                    </p>
                </form>
            </div>


            <div class="box w50 ${empty nbStock or nbStock == 0 ? "disabled" : ""}" ${empty nbStock or nbStock == 0 ? "data-message='Aucun titre à vendre'" : ""}>
                <form action="${contextPath}/market/sell?id=${market.marketId}${empty param.rev ? "" : "&rev=true"}" method="post" class="box-in">
                    <h3>Vendre des titres</h3>


                    <ul class="actions">
                        <li><span>${nbStock > 0 ? nbStock : 0}</span></li>
                    </ul>

                    <f:field type="number" label="Quantité" name="nb_stock" required="true" min="1"/>
                    <f:field type="number" label="Prix" name="price" required="true" min="1" max="99"/>

                    <p class="submit">
                        <button type="submit">Vendre</button>
                    </p>
                </form>
            </div>
        </div>
    </c:if>
</c:if>