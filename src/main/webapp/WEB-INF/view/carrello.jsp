<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title> carrello - DEMARCAF</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    <h1>Il tuo carrello</h1>

          
           
        

   
    <c:choose>        
           
        <c:when test="${carrello == null || empty carrello.articoli}">
            <p>Il tuo carrello è vuoto.</p>
            <p><a href="CatalogoServlet" class = "bottone" >Vai al catalogo</a></p>
        </c:when>

        
        <c:otherwise>
        

 
            <table border="1">
                <thead>
                    <tr>          
                    
                        <th>Prodotto</th>
                        <th>Prezzo</th>
                        <th>Quantita'</th>
                        <th>Subtotale</th>
                        <th>quantità</th>
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
                            
                                <form action="AggiornaCarrelloServlet" method="post" style="display:inline;">
                                
                                    <input type="hidden" name="idProdotto" value="${articolo.prodotto.idProdotto}"/>
                                    <input type="number" name="quantita" value="${articolo.quantita}"
                                           min="1" max="${articolo.prodotto.quantita}" style="width:60px;"/>
                                    <input type="submit" value="Aggiorna"/>
                                </form>

                                <a href="RimuoviCarrelloServlet?idProdotto=${articolo.prodotto.idProdotto}" class="bottone-rimuovi">Rimuovi</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

           
            
            <h3>Totale: &euro; <c:out value="${carrello.totale}"/></h3>

            <p>
                <a href="CatalogoServlet" class = "bottone"> Continua lo shopping</a>
                |
               <a href="CheckoutServlet" class="bottone">Procedi all'ordine</a>
                |
               <a href="SvuotaCarrelloServlet" class="bottone-rimuovi">Svuota carrello</a>
               
            </p>

        </c:otherwise>
    </c:choose>
<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>

</body>
</html>