package control;

import java.io.IOException;

import model.Carrello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Servlet che rimuove un prodotto dal carrello ( come rimuoviProdotto ) 
@WebServlet("/RimuoviCarrelloServlet")
public class RimuoviCarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Leggo l'id del prodotto da rimuovere arriva dal link ?
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

        // Recupero la sessione 
        HttpSession sessione = request.getSession(false);

        // se ce sessione e carrello rimuovo
        if (sessione != null) {
            Carrello carrello = (Carrello) sessione.getAttribute("carrello");
            if (carrello != null) {
                // riutilizzo il metodo rimuoviProdotto della classe carrello
                carrello.rimuoviProdotto(idProdotto);
            }
        }

        //  aggiornato il carrello
        response.sendRedirect("CarrelloServlet");
    }
}