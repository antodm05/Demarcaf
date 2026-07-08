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

@WebServlet("/AdminProdottiServlet")
public class AdminProdottiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);

        try {
            // L'admin vede tutti i prodotti 
            List<ProdottoBean> listaProdotti = prodottoDao.doRetrieveAll();
            request.setAttribute("listaProdotti", listaProdotti);

            // Forward alla JSP dell'area admin
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/gestioneProdotti.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?errore=adminProdotti");
        }
    }
}