<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Gestione Prodotti - Area Admin</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    <h1>Gestione Prodotti</h1>

<a href="AdminNuovoProdottoServlet" class="bottone">+ Aggiungi nuovo prodotto</a>

  
    <table border="1">
        <thead>      
            <tr>    
                <th>ID</th>
                <th>Nome</th>
                <th>Prezzo</th>
                <th>Quantita'</th>
                <th>Stato</th>
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody>     <!-- corpo -->
        
            <c:forEach var="prodotto" items="${listaProdotti}">
                <tr>
                    <td><c:out value="${prodotto.idProdotto}"/></td>
                    <td><c:out value="${prodotto.nome}"/></td>
                    <td>&euro; <c:out value="${prodotto.prezzo}"/></td>
                    <td><c:out value="${prodotto.quantita}"/></td>
                    <td>
                        <!-- Mostro Attivo o Disattivato -->
                        <c:if test="${prodotto.attivo}">Attivo</c:if>
                        <c:if test="${not prodotto.attivo}">Disattivato</c:if>
                    </td>
                    
                   <td>
<a href="AdminModificaProdottoServlet?id=${prodotto.idProdotto}" class="bottone-piccolo">Modifica</a>

    <c:choose>
        <c:when test="${prodotto.attivo}">
<a href="AdminEliminaProdottoServlet?id=${prodotto.idProdotto}" class="bottone-rimuovi">Disattiva</a>
        </c:when>
        <c:otherwise>
<a href="AdminRiattivaProdottoServlet?id=${prodotto.idProdotto}" class="bottone-piccolo">Riattiva</a>
        </c:otherwise>
    </c:choose>
</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>

</body>
</html>