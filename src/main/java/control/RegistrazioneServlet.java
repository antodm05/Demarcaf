package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteBean;

// L'URL con cui la richiamo dal form 
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; //numero di versione per le classi seralizzabile lo metto solo per non far apparrire warning giallo

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Recupero il DataSource poool e creo il DAO 
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        UtenteDao utenteDao = new UtenteDaoImpl(ds);

        try {
            //  Controllo email se esiste 
            UtenteBean esistente = utenteDao.doRetrieveByEmail(email);

            if (esistente != null) {
                // Email gia' usata
                response.sendRedirect("registrazione.html?errore=emailEsistente");
                return; 
            }

            // creo il bean e lo riempio con i dati del form
            UtenteBean nuovoUtente = new UtenteBean();
            nuovoUtente.setNome(nome);
            nuovoUtente.setCognome(cognome);
            nuovoUtente.setEmail(email);
            nuovoUtente.setPassword(password); 
            nuovoUtente.setRuolo("cliente");   // chi registra dal sito e sempre cliente

            utenteDao.doSave(nuovoUtente);

            // Uso sendRedirect per inviare l utente al login 
            response.sendRedirect("login.html?registrazione=ok");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("registrazione.html?errore=database");
        }
    }
}