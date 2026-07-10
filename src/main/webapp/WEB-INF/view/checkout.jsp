<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Completa l'ordine - DEMARCAF</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    <h1>Completa il tuo ordine</h1>

    <!-- RIEPILOGO CARRELLO -->
    <h2>Riepilogo</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Prodotto</th>
                <th>Quantita'</th>
                <th>Subtotale</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="articolo" items="${carrello.articoli}">
                <tr>
                    <td><c:out value="${articolo.prodotto.nome}"/></td>
                    <td><c:out value="${articolo.quantita}"/></td>
                    <td>&euro; <c:out value="${articolo.subtotale}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3>Totale: &euro; <c:out value="${carrello.totale}"/></h3>

    <!--  dati di spedizione + JS  -->
    
    <h2>Dati di spedizione</h2>
    <form action="ConfermaOrdineServlet" method="post" onsubmit="return validaFormCheckout()">

        <fieldset>
            <legend>Indirizzo</legend>

            <p>
                <label for="indirizzo">Indirizzo (via e numero):</label>
                <input type="text" id="indirizzo" name="indirizzo" onchange="validaIndirizzo()"/>
                <span id="erroreIndirizzo" class="messaggioErrore"></span>
            </p>

            <p>
                <label for="citta">Citta':</label>  <!-- lo riempio via ajax-->
                
                <select id="citta" name="citta">
                    <option value="">-- Prima scegli la provincia --</option>
                </select>
                <span id="erroreCitta" class="messaggioErrore"></span>
            </p>

            <p>
                <label for="cap">CAP:</label>
                <input type="text" id="cap" name="cap" onchange="validaCap()"/>
                <span id="erroreCap" class="messaggioErrore"></span>
            </p>

            <p>
                <label for="provincia">Provincia:</label>
                
                <!--via ajax lo modifico -->
                <select id="provincia" name="provincia" onchange="caricaComuni()">
                    <option value="">-- Seleziona la provincia --</option>
                    <option value="SA">Salerno (SA)</option>
                    <option value="NA">Napoli (NA)</option>
                    <option value="AV">Avellino (AV)</option>
                </select>
                <span id="erroreProvincia" class="messaggioErrore"></span>
            </p>
        </fieldset>

        <fieldset>
            <legend>Pagamento</legend>

            <p>
                <label for="metodoPagamento">Metodo di pagamento:</label>
                <select id="metodoPagamento" name="metodoPagamento" required="required">
                    <option value="carta">Carta di credito</option>
                    <option value="paypal">PayPal</option>
                    <option value="contrassegno">Contrassegno</option>
                </select>
            </p>
        </fieldset>

        <p>
            <input type="submit" value="Conferma ordine"/>
        </p>

    </form>

    <p><a href="CarrelloServlet">Torna al carrello</a></p>

    <!--alla fine scrpt di validazione -->
    
    <script src="scripts/validazioneCheckout.js"></script>
        <script src="scripts/ajaxComuni.js"></script>
    
<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>

</body>
</html>