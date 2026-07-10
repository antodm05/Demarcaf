package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import model.UtenteBean;
import utils.SecurityUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String passwordInserita = request.getParameter("password");

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        UtenteDao utenteDao = new UtenteDaoImpl(connessioneDB);

        try {
        	//cerco email
            UtenteBean utente = utenteDao.doRetrieveByEmail(email);

            // cifro la password
            String passwordCifrata = SecurityUtils.toDigest(passwordInserita);

            if (utente != null && utente.getPassword().equals(passwordCifrata)) {

                HttpSession sessione = request.getSession();
                sessione.setAttribute("utenteLoggato", utente);
                sessione.setAttribute("ruolo", utente.getRuolo());

                // Reindirizzo 
                response.sendRedirect("index.jsp");

            } else {
                response.sendRedirect("login.jsp?errore=credenzialiErrate");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?errore=database");
        }
    }
}