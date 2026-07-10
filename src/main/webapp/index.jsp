<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>DEMARCAF - Torrefazione e Assistenza</title>
    <link rel="stylesheet" href="styles/style.css"/>
</head>
<body>

<jsp:include page="/WEB-INF/view/includes/header.jsp"/>

    

    
    <h1>Benvenuto in DEMARCAF</h1>
    <p>Torrefazione artigianale e assistenza tecnica per macchine da caffe'.</p>

  
    <c:if test="${not empty sessionScope.utenteLoggato}">
        <p>Ciao, <c:out value="${sessionScope.utenteLoggato.nome}"/>!</p>
    </c:if>
    
<jsp:include page="/WEB-INF/view/includes/footer.jsp"/>
</body>
</html>