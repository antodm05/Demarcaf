<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Registrazione - DEMARCAF</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    <h1>Registrati</h1>

    <form action="RegistrazioneServlet" method="post" onsubmit="return validaForm()">

        <fieldset>
            <legend>Dati personali</legend>

            <p>
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" onchange="validaNome()"/>
                <span id="erroreNome" class="messaggioErrore"></span>
            </p>

            <p>
                <label for="cognome">Cognome:</label>
                <input type="text" id="cognome" name="cognome" onchange="validaCognome()"/>
                <span id="erroreCognome" class="messaggioErrore"></span>
            </p>

            <p>
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" onchange="validaEmail()"/>
                <span id="erroreEmail" class="messaggioErrore"></span>
            </p>

            <p>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" onchange="validaPassword()"/>
                <span id="errorePassword" class="messaggioErrore"></span>
            </p>
        </fieldset>

        <p>
            <input type="submit" value="Registrati"/>
        </p>

    </form>

    <!--  js  -->
    <script src="scripts/validazioneRegistrazione.js"></script>
<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>
</body>
</html>