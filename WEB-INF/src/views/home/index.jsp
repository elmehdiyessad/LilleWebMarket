<%@ page contentType="text/html" pageEncoding="UTF-8"%>



<ul class="actions">
    <c:if test="${!empty user and user.role == 'maker' or user.role == 'admin'}">
        <li><a href="${contextPath}/market/create">Créer un marché</a></li>
    </c:if>
</ul>

<div class="box">
    <div class="box-in">
        <table>
            <thead>
                <th>Information</th>
                <th>Echéance</th>
                <th>Variation</th>
            </thead>
            <tbody>
                <c:forEach items="${markets}" var="m">
                    <tr>
                        <td>
                            <a href="${contextPath}/market/show?id=${m.marketId}">${m.title}</a>
                        </td>
                        <td class="center">
                            <time class="endtime">
                                <fmt:formatDate value="${m.term}" pattern="dd MMM yyyy" />
                            </time>
                        </td>
                        <td class="variation ${m.variation < 0 ? "negative" : "positive"} center">
                            ${m.variation < 0 ? "-" : "+"} ${m:abs(m.variation)} %
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>