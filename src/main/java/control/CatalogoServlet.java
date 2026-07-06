package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import model.ProdottoBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recupero il DataSource e creo il DAO dei prodotti
        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);

        try {
            //  Chiedo la lista di tutti i prodotti attivi NEL DAO
            List<ProdottoBean> listaProdotti = prodottoDao.doRetrieveAllActive();

            //  la JSP potra' leggerla
            request.setAttribute("listaProdotti", listaProdotti);

            //chiamo la JSP che mostrera i prodotti forward 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/catalogo.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace(); // errore db cosa è andato storto 
            response.sendRedirect("index.html?errore=catalogo");
        }
    }
}