<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- menu universale -->
<header class="header-sito">
    <div class="logo">
        <a href="index.jsp">DEMARCAF</a>
    </div>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="CatalogoServlet">Catalogo</a></li>

            <c:choose>
                <c:when test="${empty sessionScope.utenteLoggato}">
                    <li><a href="login.jsp">Accedi</a></li>
                    <li><a href="registrazione.jsp">Registrati</a></li>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.ruolo == 'admin'}">
                        <li><a href="AdminProdottiServlet">Gestione Prodotti</a></li>
                        <li><a href="AdminOrdiniServlet">Gestione Ordini</a></li>
                    </c:if>
                    <c:if test="${sessionScope.ruolo == 'cliente'}">
                        <li><a href="CarrelloServlet">Carrello</a></li>
                        <li><a href="MieiOrdiniServlet">I miei ordini</a></li>
                    </c:if>
                    <li><a href="LogoutServlet">Logout</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>