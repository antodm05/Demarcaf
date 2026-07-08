<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Gestione Prodotti - Area Admin</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>

    <h1>Gestione Prodotti</h1>

    <!--  per aggiungere un nuovo prodotto -->
    <p><a href="AdminNuovoProdottoServlet">+ Aggiungi nuovo prodotto</a></p>

  
    <table border="1">
        <thead>      <!--  intestazione della tabella -->
            <tr>     <!--  riga-->
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
                        <!-- per modificare ed eliminare -->
                        <a href="AdminModificaProdottoServlet?id=${prodotto.idProdotto}">Modifica</a>
                        <a href="AdminEliminaProdottoServlet?id=${prodotto.idProdotto}">Elimina</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <p><a href="index.jsp">Torna alla home</a></p>

</body>
</html>