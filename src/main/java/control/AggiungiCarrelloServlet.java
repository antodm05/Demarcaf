package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import model.Carrello;
import model.ProdottoBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Servlet che aggiunge un prodotto al carrello 
@WebServlet("/AggiungiCarrelloServlet")
public class AggiungiCarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Leggo l'id del prodotto e la quantita 
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        // Recupero il prodotto completo dal database
        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);

        try {
            ProdottoBean prodotto = prodottoDao.doRetrieveById(idProdotto);

            // Recupero la sessione , se poi non esiste la creo
            HttpSession sessione = request.getSession();

            //  Prendo il carrello dalla sessione
            
            Carrello carrello = (Carrello) sessione.getAttribute("carrello");
            if (carrello == null) {
                carrello = new Carrello();
                sessione.setAttribute("carrello", carrello);
            }

            // Aggiungo il prodotto 
            carrello.aggiungiProdotto(prodotto, quantita);

            //  carrello aggiornato
            response.sendRedirect("CarrelloServlet");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("CatalogoServlet?errore=carrello");
        }
    }
}