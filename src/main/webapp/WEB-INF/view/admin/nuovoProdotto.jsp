<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Nuovo Prodotto - Area Admin</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    <h1>Aggiungi nuovo prodotto</h1>

    <!-- multipart/form-data serve per l'upload di file. -->
    <form action="AdminSalvaProdottoServlet" method="post" enctype="multipart/form-data">

        <fieldset>
            <legend>Dati prodotto</legend>

            <p>
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" required="required"/>
            </p>

            <p>
                <label for="descrizione">Descrizione:</label>
                <textarea id="descrizione" name="descrizione" rows="4" cols="40"></textarea>
            </p>

            <p>
                <label for="prezzo">Prezzo (&euro;):</label>
                <input type="number" id="prezzo" name="prezzo" step="0.01" min="0" required="required"/>
            </p>

            <p>
                <label for="quantita">Quantita':</label>
                <input type="number" id="quantita" name="quantita" min="0" required="required"/>
            </p>

            <p>
                <label for="categoria">Categoria:</label>
                
                <!-- Menu a tendina -->
                
                <select id="categoria" name="categoria" required="required">
                
                    <c:forEach var="categoria" items="${listaCategorie}">
                        <option value="${categoria.idCategoria}">
                            <c:out value="${categoria.nome}"/>
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label for="immagine">Immagine:</label>
                
                <!-- creo il pulsante per scegliere un file -->
                
                <input type="file" id="immagine" name="immagine" accept="image/*"/>
            </p>
        </fieldset>

        <p>
            <input type="submit" value="Salva prodotto"/>
        </p>

    </form>

    <p><a href="AdminProdottiServlet">Torna all'elenco prodotti</a></p>
<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>

</body>
</html>