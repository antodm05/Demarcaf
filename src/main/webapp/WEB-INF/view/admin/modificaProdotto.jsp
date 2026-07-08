<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Modifica Prodotto - Area Admin</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>

    <h1>Modifica prodotto</h1>

    <form action="AdminAggiornaProdottoServlet" method="post" enctype="multipart/form-data">

        <!-- Campo nascosto con l'id: serve alla servlet per sapere che prodotto aggiornare -->
        <input type="hidden" name="id" value="${prodotto.idProdotto}"/>

        <fieldset>
            <legend>Dati prodotto</legend>

            <p>
                <label for="nome">Nome:</label>
                <!-- value gia' riempito con il valore attuale del prodotto -->
                <input type="text" id="nome" name="nome" value="${prodotto.nome}" required="required"/>
            </p>

            <p>
                <label for="descrizione">Descrizione:</label>
                <textarea id="descrizione" name="descrizione" rows="4" cols="40"><c:out value="${prodotto.descrizione}"/></textarea>
            </p>

            <p>
                <label for="prezzo">Prezzo (&euro;):</label>
                <input type="number" id="prezzo" name="prezzo" step="0.01" min="0" value="${prodotto.prezzo}" required="required"/>
            </p>

            <p>
                <label for="quantita">Quantita':</label>
                <input type="number" id="quantita" name="quantita" min="0" value="${prodotto.quantita}" required="required"/>
            </p>

            <p>
           
    <label for="categoria">Categoria:</label>

    <!-- Il menu a tendina -->
    <select id="categoria" name="categoria" required="required">

        <!-- Ciclo su OGNI categoria presente nel db -->
        <c:forEach var="categoria" items="${listaCategorie}">
        
         <!-- Per ogni categoria deve essere quella del prodotto che sto modificando, c:choose che funziona come un if/else -->
         
<!-- questa categoria coincide con quella del prodotto - Confronto l'id della categoria del ciclo con l id categoria del prodotto -->

<!-- Sono uguali: aggiungo selected="selected" cosi' il menu si apre gia' su questa categoria -->

  <!--  ALTRIMENTI: tutte le altre categorie -->
            <c:choose>            
            

                <c:when test="${categoria.idCategoria == prodotto.idCategoria}">  
                                    
                    <option value="${categoria.idCategoria}" selected="selected"> 
                    
                        <c:out value="${categoria.nome}"/>
                    </option>

                </c:when>

                <c:otherwise>               
                

                    <!--  SENZA selected -->
                    <option value="${categoria.idCategoria}">
                        <c:out value="${categoria.nome}"/>
                    </option>

                </c:otherwise>

            </c:choose>

        </c:forEach>
    </select>
</p>

            <p>
                <label for="immagine">Nuova immagine -lascia vuoto per non cambiarla-:</label>
                <input type="file" id="immagine" name="immagine" accept="image/*"/>
            </p>
        </fieldset>

        <p>
            <input type="submit" value="Salva modifiche"/>
        </p>

    </form>

    <p><a href="AdminProdottiServlet">Torna all'elenco prodotti</a></p>

</body>
</html>