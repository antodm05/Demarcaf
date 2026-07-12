<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    
    <title>Catalogo - DEMARCAF</title>
    
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    <h1>Il nostro catalogo</h1>

    <p><a href="CarrelloServlet" class="bottone">Vai al carrello</a></p>

    <!--  per ogni categoria -->
    <c:forEach var="categoria" items="${listaCategorie}">

        <!-- Titolo della categoria -->
        <h2 class="titolo-categoria"><c:out value="${categoria.nome}"/></h2>

        <!-- per 1 categoria -->
        <c:forEach var="prodotto" items="${listaProdotti}">
            <c:if test="${prodotto.idCategoria == categoria.idCategoria}">

                <div class="catalogo-prodotto">
                    <h3><c:out value="${prodotto.nome}"/></h3>
                    <p><c:out value="${prodotto.descrizione}"/></p>
                    <p>Prezzo: &euro; <c:out value="${prodotto.prezzo}"/></p>
                    <p>Disponibilita': <c:out value="${prodotto.quantita}"/></p>

                    <c:if test="${not empty prodotto.immagine}">
                        <img src="images/${prodotto.immagine}" alt="${prodotto.nome}" width="150"/>
                    </c:if>

                    <form action="AggiungiCarrelloServlet" method="post">

                        <input type="hidden" name="idProdotto" value="${prodotto.idProdotto}"/>

                        <label for="qta${prodotto.idProdotto}">Quantita':</label>
                        <input type="number" id="qta${prodotto.idProdotto}" name="quantita"
                               value="1" min="1" max="${prodotto.quantita}"/>

                        <input type="submit" value="Aggiungi al carrello"/>
                    </form>
                </div>
                <hr/>

            </c:if>
        </c:forEach>

    </c:forEach>

<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>

</body>
</html>