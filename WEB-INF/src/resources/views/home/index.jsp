<%@ page import="src.entity.Market" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<h2>Accueil</h2>

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
        <% for(Market m : (ArrayList<Market>) request.getAttribute("markets")) { %>
        <tr>
            <td>
                <a href="#">${m.titre}</a>
            </td>
            <td class="center">
                <time class="endtime">${m.echeance}</time>
            </td>
            <td class="variation negative center">
                ??%
            </td>
        </tr>
        <% } %>
    </tbody>
</table>