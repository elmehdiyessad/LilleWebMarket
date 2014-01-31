<%@ page import="src.entity.Market" %>
<%@ page import="java.util.ArrayList" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="actions">
    <li><a href="<%= request.getContextPath() %>">Mettre à jour</a></li>
    <li><a href="<%= request.getContextPath() %>/market/create">Ajouter une information</a></li>
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
        <c:forEach items="${markets}" var="m">
            <tr>
                <td>
                    <a href="<%= request.getContextPath() %>/market/show?id=${m.id}">${m.title}</a>
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