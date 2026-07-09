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

    <h1>Il nostro catalogo</h1>

  
    
    <p><a href="CarrelloServlet">Vai al carrello</a></p>

    <!--  prodotti attivi -->
    <c:forEach var="prodotto" items="${listaProdotti}">

    <div class="catalogo-prodotto">
            <h3><c:out value="${prodotto.nome}"/></h3>
            <p><c:out value="${prodotto.descrizione}"/></p>
            <p>Prezzo: &euro; <c:out value="${prodotto.prezzo}"/></p>
            <p>Disponibilita': <c:out value="${prodotto.quantita}"/></p>

            <!-- Mostro l'immagine SOLO se il prodotto ne ha una -->
            
            <c:if test="${not empty prodotto.immagine}">
                <img src="images/${prodotto.immagine}" alt="${prodotto.nome}" width="150"/>
            </c:if>

            
            
            <form action="AggiungiCarrelloServlet" method="post">
            
                <!-- Campo nascosto con l'id del prodotto da aggiungere serve per riconoscere il prodotto dall id -->
                
                <input type="hidden" name="idProdotto" value="${prodotto.idProdotto}"/>

                
                
                <label for="qta${prodotto.idProdotto}">Quantita':</label>
                <input type="number" id="qta${prodotto.idProdotto}" name="quantita"
                       value="1" min="1" max="${prodotto.quantita}"/>

                <input type="submit" value="Aggiungi al carrello"/>
            </form>
        </div>
        <hr/>


    </c:forEach>

</body>
</html>