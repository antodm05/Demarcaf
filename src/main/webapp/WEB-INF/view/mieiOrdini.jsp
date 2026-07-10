<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>I miei ordini - DEMARCAF</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>

    <h1>I miei ordini</h1>
 
    <c:choose>
        <c:when test="${empty listaOrdini}">
            <p>Non hai ancora effettuato ordini.</p>
            <p><a href="CatalogoServlet">Vai al catalogo</a></p>
        </c:when>
        <c:otherwise>

            <table border="1">
                <thead>
                    <tr>
                        <th>N. Ordine</th>
                        <th>Data</th>
                        <th>Totale</th>
                        <th>Stato</th>
                        <th>Spedizione</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ordine" items="${listaOrdini}">
                        <tr>
                            <td><c:out value="${ordine.idOrdine}"/></td>
                            <td><c:out value="${ordine.data}"/></td>
                            <td>&euro; <c:out value="${ordine.totale}"/></td>
                            <td><c:out value="${ordine.stato}"/></td>
                            <td>
                                <c:out value="${ordine.indirizzo}"/>,
                                <c:out value="${ordine.citta}"/>
                                (<c:out value="${ordine.provincia}"/>)
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:otherwise>
    </c:choose>

    <p><a href="index.jsp">Torna alla home</a></p>
<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>

</body>
</html>