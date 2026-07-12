<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    
    <title>Dettaglio Ordine - DEMARCAF</title>
    
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>

    <jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    <h1>Dettaglio Ordine #<c:out value="${idOrdine}"/></h1>

    <c:choose>
        <c:when test="${empty listaDettagli}">
            <p>Nessun prodotto trovato per questo ordine.</p>
        </c:when>
        <c:otherwise>
            <table border="1">
                <thead>
                    <tr>
                        <th>Prodotto</th>
                        <th>Quantita'</th>
                        <th>Prezzo unitario (pagato)</th>
                        <th>Subtotale</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dettaglio" items="${listaDettagli}">
                        <tr>
                            <td><c:out value="${dettaglio.nomeProdotto}"/></td>
                            <td><c:out value="${dettaglio.quantita}"/></td>
                            <td>&euro; <c:out value="${dettaglio.prezzoUnitario}"/></td>
                            <td>&euro; <c:out value="${dettaglio.prezzoUnitario * dettaglio.quantita}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

<a href="MieiOrdiniServlet" class="bottone">Torna ai miei ordini</a>

    <jsp:include page="/WEB-INF/view/includes/footer.jsp"/>

</body>
</html>