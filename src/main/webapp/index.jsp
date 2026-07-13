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

    
    <section class="hero"> </section>

    
    <c:if test="${not empty sessionScope.utenteLoggato}">
        <p class="saluto">Bentornato, <c:out value="${sessionScope.utenteLoggato.nome}"/>!</p>
    </c:if>

  
    <section class="contenuto-con-lati">

        <figure class="foto-lato">
        <img src="images/taz.jpeg" alt="tazzina" class="foto-lato"/>
                    <figcaption>Il gusto autentico del caffè</figcaption>
        
        </figure>
        <div class="colonna-centrale">
            <h2>Chi siamo</h2>
            <p>
                DEMARCAF è una torrefazione artigianale con sede a Salerno di alta qualità.
            Offriamo ottimo caffè sia in grani che in cialde per gustarlo in compagnia.
            Oltre al classico caffè troverete prodotti pubblicitari (zucchero/penne/calendari)
            per i vostri bar e aziende.
            </p>
            <p>
                <a href="CatalogoServlet" class="bottone">Scopri il catalogo</a>
            </p>

            <h2>Il mondo DEMARCAF</h2>
            <p>
             Raggiungiamo bar, uffici e privati in tutta Salerno e provincia,
            con consegne e assistenza tecnica
             per le macchine da caffè ,  
             direttamente sul posto(Salerno/Napoli/Avellino)
            </p>
        </div>

       
        <figure class="foto-lato">
            <img src="images/singola cialda.jpg" alt="Cialde DEMARCAF"/>
            <figcaption>Le nostre cialde artigianali</figcaption>
        </figure>
        
    </section>

  
   <section class="foto-sotto">
        <figure>
            <img src="images/furgone papà.jpg" alt="Tazzina DEMARCAF"/>
             <figcaption>Sempre pronti per voi</figcaption>
            
        </figure>
    </section>

    <jsp:include page="/WEB-INF/view/includes/footer.jsp"/>

</body>
</html>