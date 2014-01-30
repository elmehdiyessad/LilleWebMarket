<%@ page import="src.entity.Marche" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<h2>Accueil</h2>

<ul class="actions">
    <li><a href="#">Mettre à jour</a></li>
    <li><a href="#">Ajouter une information</a></li>
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
        <% for(Marche m : (ArrayList<Marche>) request.getAttribute("marches")) { %>
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