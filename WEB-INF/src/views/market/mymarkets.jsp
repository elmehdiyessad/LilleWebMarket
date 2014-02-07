<%@ page contentType="text/html" pageEncoding="UTF-8"%>



<ul class="actions">
    <li><a href="${contextPath}/market/create">Créer un marché</a></li>
</ul>

<div class="row">
    <div class="box w50">
        <div class="box-in">
            <table>
                <caption>En cours</caption>
                <thead>
                    <th>Information</th>
                    <th>Echéance</th>
                </thead>
                <tbody>
                    <c:forEach items="${markets}" var="m">
                        <c:if test="${m.term > now}">
                            <tr>
                                <td>
                                    <a href="${contextPath}/market/show?id=${m.marketId}">${m.title}</a>
                                </td>
                                <td class="center">
                                    <time class="endtime">
                                        <fmt:formatDate value="${m.term}" pattern="dd/MM/yy" />
                                    </time>
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
                <caption>En attente de validation</caption>
                <thead>
                    <th>Information</th>
                </thead>
                <tbody>
                    <c:forEach items="${markets}" var="m">
                        <c:if test="${m.term <= now}">
                            <tr>
                                <td>
                                    <a href="${contextPath}/market/show?id=${m.marketId}">${m.title}</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>