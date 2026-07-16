package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.OrdineDao;
import dao.OrdineDaoImpl;
import model.Carrello;
import model.OrdineBean;
import model.UtenteBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ConfermaOrdineServlet")
public class ConfermaOrdineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         
        HttpSession sessione = request.getSession(false);

        UtenteBean utente = null;
        Carrello carrello = null;
        if (sessione != null) {
            utente = (UtenteBean) sessione.getAttribute("utenteLoggato");
            carrello = (Carrello) sessione.getAttribute("carrello");
        }
        if (utente == null) {
            response.sendRedirect("login.jsp?errore=devLoggarti");
            return;
        }
        if (carrello == null || carrello.getArticoli().isEmpty()) {
            response.sendRedirect("CarrelloServlet?errore=carrelloVuoto");
            return;
        }

        
        String indirizzo = request.getParameter("indirizzo");
        String citta = request.getParameter("citta");
        String cap = request.getParameter("cap");
        String provincia = request.getParameter("provincia");
        String metodoPagamento = request.getParameter("metodoPagamento");
        String note = request.getParameter("note");

       
        OrdineBean ordine = new OrdineBean();
        ordine.setIdUtente(utente.getIdUtente());       
        ordine.setTotale(carrello.getTotale());         
        ordine.setIndirizzo(indirizzo);
        ordine.setCitta(citta);
        ordine.setCap(cap);
        ordine.setProvincia(provincia);
        ordine.setMetodoPagamento(metodoPagamento);
        ordine.setNote(note);
        
        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        OrdineDao ordineDao = new OrdineDaoImpl(connessioneDB);

        try {
            
            ordineDao.doSave(ordine, carrello.getArticoli());

             
            carrello.svuota();

      
            response.sendRedirect("ordineConfermato.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("CarrelloServlet?errore=ordineFallito");
        }
    }
}