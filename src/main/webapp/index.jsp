<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>DEMARCAF - Torrefazione e Assistenza</title>
</head>
<body>

    
    <nav>
        <ul>
            
            <li><a href="index.jsp">Home</a></li>
            <li><a href="CatalogoServlet">Catalogo</a></li>
            
 <!-- utente non loggato  -->
    <!--  loggato -->
   <!-- vedo se admin -->
       <!--cliente  -->
                        
    
            <c:choose>
               
                <c:when test="${empty sessionScope.utenteLoggato}">
                    <li><a href="login.html">Accedi</a></li>
                    <li><a href="registrazione.html">Registrati</a></li>
                </c:when>

             
                <c:otherwise>

                    <c:if test="${sessionScope.ruolo == 'admin'}">
                        <li><a href="AdminProdottiServlet">Gestione Prodotti</a></li>
                        <li><a href="AdminOrdiniServlet">Gestione Ordini</a></li>
                    </c:if>

                    <c:if test="${sessionScope.ruolo == 'cliente'}">
                        <li><a href="CarrelloServlet">Carrello</a></li>
                        <li><a href="MieiOrdiniServlet">I miei ordini</a></li>
                    </c:if>

                    
                    <li><a href="LogoutServlet">Logout</a></li>

                </c:otherwise>
            </c:choose>
        </ul>
    </nav>

    
    <h1>Benvenuto in DEMARCAF</h1>
    <p>Torrefazione artigianale e assistenza tecnica per macchine da caffe'.</p>

  
    <c:if test="${not empty sessionScope.utenteLoggato}">
        <p>Ciao, <c:out value="${sessionScope.utenteLoggato.nome}"/>!</p>
    </c:if>

</body>
</html>