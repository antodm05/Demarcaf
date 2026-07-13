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

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        UtenteDao utenteDao = new UtenteDaoImpl(ds);

        try {
            UtenteBean esistente = utenteDao.doRetrieveByEmail(email);

            if (esistente != null) {
                response.sendRedirect("registrazione.jsp?errore=emailEsistente");
                return; 
            }

            UtenteBean nuovoUtente = new UtenteBean();
            nuovoUtente.setNome(nome);
            nuovoUtente.setCognome(cognome);
            nuovoUtente.setEmail(email);
            nuovoUtente.setPassword(password); 
            nuovoUtente.setRuolo("cliente");  

            utenteDao.doSave(nuovoUtente);

             
            response.sendRedirect("login.jsp?registrazione=ok");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("registrazione.jsp?errore=database");
        }
    }
}