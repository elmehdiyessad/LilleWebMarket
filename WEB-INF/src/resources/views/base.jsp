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
                    <a href="<%= request.getContextPath() %>">
                        LilleWeb<span class="market">Market</span>
                    </a>
                </h1>
                <div class="logbox">
                    <c:if test="${!empty pageContext.request.userPrincipal}">
                        <div class="infos">
                            <div class="name">
                                <span class="first-name">${user.firstName}</span>
                                <span class="last-name">${user.lastName}</span>
                            </div>
                            <span class="cash">Cash : ${user.cash}€</span>
                        </div>
                        <a href="<%= request.getContextPath() %>/security/logout" class="logout">Deconnexion</a>
                    </c:if>
                    <c:if test="${empty pageContext.request.userPrincipal}">
                        <a href="<%= request.getContextPath() %>/security/register" class="login">Inscription</a>
                        <a href="<%= request.getContextPath() %>/security/login?from=${urlFrom}" class="login">Connexion</a>
                    </c:if>
                </div>
            </div>
        </header>

        <main role="main">
            <div class="wrapper">
                <c:forEach items="${flashBag}" var="f">
                    <div class="flash ${f.key}">
                        ${f.value}
                    </div>
                </c:forEach>
            </div>
            <div class="wrapper ${bodyClass}">
                <h2>${title}</h2>

                <jsp:include page="${template}" flush="true"/>
            </div>
        </main>

        <footer>
            <div class="wrapper">
                <p>
                    LilleWeb<span class="market">Market</span> - Projet web LP SIL DA2I - IUT A, Lille 1
                </p>
            </div>
        </footer>
    </div>




    <script src="<%= request.getContextPath() %>/static/js/d3.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/d3.layout.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/rickshaw.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/main.js"></script>
</body>
</html>