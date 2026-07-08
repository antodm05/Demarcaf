<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title> carrello - DEMARCAF</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>

    <h1>Il tuo carrello</h1>

           <!--  carrello vuoto o inesistente -->
            <!--  blocco di scelta -->
            <!-- carrello non vuoto -->
<!-- righe -->
 <!-- per ogni prodotto , nome , prezzo , quantita e subtotale -->
   
    <c:choose>        
           
        <c:when test="${carrello == null || empty carrello.articoli}">
            <p>Il tuo carrello e' vuoto.</p>
            <p><a href="CatalogoServlet">Vai al catalogo</a></p>
        </c:when>

        
        <c:otherwise>
        

 
            <table border="1">
                <thead>
                    <tr>          
                    
                        <th>Prodotto</th>
                        <th>Prezzo</th>
                        <th>Quantita'</th>
                        <th>Subtotale</th>
                        <th>Azioni</th>
                    </tr>
                </thead>
                <tbody>
                   
                    
                    <c:forEach var="articolo" items="${carrello.articoli}">
                        <tr>
                            <td><c:out value="${articolo.prodotto.nome}"/></td>
                            <td>&euro; <c:out value="${articolo.prodotto.prezzo}"/></td>
                            <td><c:out value="${articolo.quantita}"/></td>
                            
                            <td>&euro; <c:out value="${articolo.subtotale}"/></td>
                            <td>
                                <a href="RimuoviCarrelloServlet?idProdotto=${articolo.prodotto.idProdotto}">Rimuovi</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Totale del carrello -->
            
            <h3>Totale: &euro; <c:out value="${carrello.totale}"/></h3>

            <p>
                <a href="CatalogoServlet">Continua lo shopping</a>
                |
                <a href="CheckoutServlet">Procedi all'ordine</a>
            </p>

        </c:otherwise>
    </c:choose>

</body>
</html>