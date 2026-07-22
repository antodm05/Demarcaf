<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Ordine confermato - DEMARCAF</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

<body>
    <h1>Grazie per il tuo ordine!</h1>
    <p>Il tuo ordine è stato registrato con successo.</p>
    <p><a href="CatalogoServlet" class = "bottone">Torna al catalogo</a></p>
    <jsp:include page="/WEB-INF/view/includes/footer.jsp"/>
</body>
</html>