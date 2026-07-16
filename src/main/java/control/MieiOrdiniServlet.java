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

@WebServlet("/MieiOrdiniServlet")
public class MieiOrdiniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessione = request.getSession(false);

        UtenteBean utente = null;
        if (sessione != null) {
            utente = (UtenteBean) sessione.getAttribute("utenteLoggato");
        }
        if (utente == null) {
            response.sendRedirect("login.jsp?errore=devLoggarti");
            return;
        }

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        OrdineDao ordineDao = new OrdineDaoImpl(connessioneDB);

        try {
            List<OrdineBean> listaOrdini = ordineDao.doRetrieveByUtente(utente.getIdUtente());

            request.setAttribute("listaOrdini", listaOrdini);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/mieiOrdini.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?errore=ordini");
        }
    }
}