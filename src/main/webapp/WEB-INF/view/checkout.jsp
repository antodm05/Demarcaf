<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Completa l'ordine - DEMARCAF</title>
</head>
<body>

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

    <!-- form dati di spedizione con validazione JS  -->
    
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
                <label for="citta">Citta':</label>
                <input type="text" id="citta" name="citta" onchange="validaCitta()"/>
                <span id="erroreCitta" class="messaggioErrore"></span>
            </p>

            <p>
                <label for="cap">CAP:</label>
                <input type="text" id="cap" name="cap" onchange="validaCap()"/>
                <span id="erroreCap" class="messaggioErrore"></span>
            </p>

            <p>
                <label for="provincia">Provincia (sigla):</label>
                <input type="text" id="provincia" name="provincia" maxlength="2" onchange="validaProvincia()"/> 
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

</body>
</html>