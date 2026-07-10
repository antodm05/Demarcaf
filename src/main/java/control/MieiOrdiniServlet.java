package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.OrdineDao;
import dao.OrdineDaoImpl;
import model.OrdineBean;
import model.UtenteBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Servlet che mostra lo storico degli ordini del cliente loggato
@WebServlet("/MieiOrdiniServlet")
public class MieiOrdiniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupero la sessione (senza crearne una nuova)
        HttpSession sessione = request.getSession(false);

        // 1. Controllo che l'utente sia loggato
        UtenteBean utente = null;
        if (sessione != null) {
            utente = (UtenteBean) sessione.getAttribute("utenteLoggato"); //recupero utente dalla sessione
        }
        if (utente == null) {
            response.sendRedirect("login.jsp?errore=devLoggarti");
            return;
        }

        // 2. Recupero il DataSource e creo il DAO
        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        OrdineDao ordineDao = new OrdineDaoImpl(connessioneDB);

        try {
            //  Carico gli ordini di QUESTO utente uso il suo id 
            List<OrdineBean> listaOrdini = ordineDao.doRetrieveByUtente(utente.getIdUtente());

            //  Metto la lista nella request per la JSP
            request.setAttribute("listaOrdini", listaOrdini);

            //  Mostro la pagina degli ordini
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/mieiOrdini.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?errore=ordini");
        }
    }
}