<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Catalogo - DEMARCAF</title>
</head>
<body>

    <h1>Il nostro catalogo</h1>

    <!-- Ciclo su tutta la lista di prodotti -->
    <c:forEach var="prodotto" items="${listaProdotti}">

        <div>
<!-- chiama i getter -->            
            <h3><c:out value="${prodotto.nome}"/></h3>
            <p><c:out value="${prodotto.descrizione}"/></p>
            <p>Prezzo: &euro; <c:out value="${prodotto.prezzo}"/></p>
            <p>Disponibilita': <c:out value="${prodotto.quantita}"/></p>
        </div>
        <hr/> <!-- linea  orizzontale  -->            


    </c:forEach>

</body>
</html>