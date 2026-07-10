package control;

import java.io.IOException;

import model.Carrello;
import model.UtenteBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Servlet che mostra il form per completare l'ordine (dati di spedizione)
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupero la sessione 
        HttpSession sessione = request.getSession(false);

        // Controllo che l'utente sia loggato 
        UtenteBean utente = null;
        if (sessione != null) {
            utente = (UtenteBean) sessione.getAttribute("utenteLoggato");
        }
        if (utente == null) {
            response.sendRedirect("login.jsp?errore=devLoggarti");
            return; 
        }

        // recup carrello sempre da sessione 
        Carrello carrello = (Carrello) sessione.getAttribute("carrello");
        if (carrello == null || carrello.getArticoli().isEmpty()) {
            response.sendRedirect("CarrelloServlet?errore=carrelloVuoto");
            return; 
        }

       
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/checkout.jsp");
        dispatcher.forward(request, response);
    }
}