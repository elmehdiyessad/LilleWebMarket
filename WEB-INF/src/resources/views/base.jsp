<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>${title} | LilleWebMarket</title>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/main.css">
</head>
<body>
    <div id="page">
        <header id="header">
            <div class="wrapper">
                <h1>
                    <a href="<%= request.getContextPath() %>">LilleWebMarket</a>
                </h1>
                <div class="logbox">
                    <% if(request.getUserPrincipal() != null) { %>
                        <img src="http://lorempicsum.com/futurama/60/60/6" class="avatar">
                        <div class="infos">
                            <span class="pseudo">${user.firstName}</span>
                            <div class="cash">
                                <span class="value">Cash : ${user.cash}€</span>
                            </div>

                            <ul>
                                <li>
                                    <span class="def">Mes Bons</span>
                                    <span class="value positive">??</span>
                                </li>
                                <li>
                                    <span class="def">Valeur</span>
                                    <span class="value">??</span>
                                </li>
                                <li>
                                    <span class="def">Perf.</span>
                                    <span class="value">??%</span>
                                </li>
                                <li>
                                    <span class="def">Gain</span>
                                    <span class="value">???</span>
                                </li>
                            </ul>
                        </div>
                    <% } else { %>
                        <a href="<%= request.getContextPath() %>/security/login" class="login">Connexion</a>
                    <% } %>
                </div>
            </div>
        </header>

        <main role="main">
            <div class="wrapper">
                <h2>${title}</h2>

                <jsp:include page="${template}" flush="true"/>
            </div>
        </main>

        <footer>
            <div class="wrapper">
                <p>
                    LilleWebMarket - Projet web LP SIL DA2I - IUT A, Lille 1
                </p>
            </div>
        </footer>
    </div>
</body>
</html>