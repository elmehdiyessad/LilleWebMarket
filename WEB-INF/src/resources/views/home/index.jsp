﻿<%@ page import="src.entity.Market" %>
<%@ page import="java.util.ArrayList" %>



<ul class="actions">
    <li><a href="<%= request.getContextPath() %>">Actualiser</a></li>
    <li><a href="<%= request.getContextPath() %>/market/create">Ajouter une information</a></li>
</ul>

<div class="box">
    <div class="box-in">
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
                <c:forEach items="${markets}" var="m">
                    <tr>
                        <td>
                            <a href="<%= request.getContextPath() %>/market/show?id=${m.marketId}">${m.title}</a>
                        </td>
                        <td class="center">
                            <time class="endtime">${m.term}</time>
                        </td>
                        <td class="variation negative center">
                            ??%
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>