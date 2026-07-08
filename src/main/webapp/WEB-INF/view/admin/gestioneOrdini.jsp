<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Gestione Ordini - Area Admin</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>

    <h1>Gestione Ordini</h1>

   
    <h2>Filtra ordini</h2>

    <!-- Filtro per date , get cosi i filtri restano visibili nell'URL -->
    <form action="AdminOrdiniServlet" method="get">
        <fieldset>
            <legend>Per data</legend>
            <p>
                <label for="dataInizio">Dal:</label>
                <input type="date" id="dataInizio" name="dataInizio"/>

                <label for="dataFine">Al:</label>
                <input type="date" id="dataFine" name="dataFine"/>

                <input type="submit" value="Filtra per data"/>
            </p>
        </fieldset>
    </form>

    <!-- Filtro per email -->
    <form action="AdminOrdiniServlet" method="get">
        <fieldset>
            <legend>Per cliente</legend>
            <p>
                <label for="email">Email cliente:</label>
                <input type="text" id="email" name="email"/>
                <input type="submit" value="Cerca cliente"/>
            </p>
        </fieldset>
    </form>

   
    <p><a href="AdminOrdiniServlet">Mostra tutti gli ordini</a></p>

    <!-- tab ordini -->
    <h2>Elenco ordini</h2>

    <c:choose>
        <c:when test="${empty listaOrdini}">
            <p>Nessun ordine trovato.</p>
        </c:when>
        <c:otherwise>
            <table border="1">
                <thead>
                    <tr>
                        <th>N. Ordine</th>
                        <th>Data</th>
                        <th>Cliente</th>
                        <th>Totale</th>
                        <th>Stato</th>
                        <th>Citta'</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ordine" items="${listaOrdini}">
                        <tr>
                            <td><c:out value="${ordine.idOrdine}"/></td>
                            <td><c:out value="${ordine.data}"/></td>
                            <td><c:out value="${ordine.emailCliente}"/></td>
                            <td>&euro; <c:out value="${ordine.totale}"/></td>
                            <td><c:out value="${ordine.stato}"/></td>
                            <td><c:out value="${ordine.citta}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <p><a href="AdminProdottiServlet">Gestione prodotti</a> | <a href="index.jsp">Home</a></p>

</body>
</html>