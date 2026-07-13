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

@WebServlet("/AggiungiCarrelloServlet")
public class AggiungiCarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);

        try {
            ProdottoBean prodotto = prodottoDao.doRetrieveById(idProdotto);

            HttpSession sessione = request.getSession();

            
            Carrello carrello = (Carrello) sessione.getAttribute("carrello");
            if (carrello == null) {
                carrello = new Carrello();
                sessione.setAttribute("carrello", carrello);
            }

            carrello.aggiungiProdotto(prodotto, quantita);

            response.sendRedirect("CarrelloServlet");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("CatalogoServlet?errore=carrello");
        }
    }
}