package control;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Il filtro intercetta TUTTE le richieste ("/*") per controllare gli accessi
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Converto le richieste generiche in richieste HTTP (per leggere URL e sessione)
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Prendo il nome della risorsa richiesta (es. "AdminProdottiServlet")
        String risorsa = req.getRequestURI();

        // Controllo se la richiesta e' per una pagina admin (contiene "Admin")
        if (risorsa.contains("Admin")) {

            // Recupero la sessione SENZA crearne una nuova 
            HttpSession sessione = req.getSession(false);

            // Leggo il ruolo salvato in sessione al login (se esiste)
            String ruolo = null;
            if (sessione != null) {
                ruolo = (String) sessione.getAttribute("ruolo");
            }

            //  lo mando al login SE è DIVERSO DA ADMIN
            if (ruolo == null || !ruolo.equals("admin")) {
                res.sendRedirect(req.getContextPath() + "/login.jsp?errore=nonAutorizzato");
                return; // fermo qui: non lascio passare la richiesta
            }
            
        }

        // Se e' tutto ok  passare la richiesta
        chain.doFilter(request, response); // se questo non viene chiamato la richiesta si ferma
    }
    
    //codice prima di doFILTER è per l'andata quello dopo il ritorno 
}