<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login - DEMARCAF</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    <h1>Accedi</h1>

    <form action="LoginServlet" method="post">

        <fieldset>
            <legend>Credenziali</legend>

            <p>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required="required"/>
            </p>

            <p>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required="required"/>
            </p>
        </fieldset>

        <p>
            <input type="submit" value="Accedi"/>
        </p>

    </form>

    <p>Non hai un account? <a href="registrazione.jsp" class = "bottone">Registrati</a></p>
    
<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>
</body>
</html>