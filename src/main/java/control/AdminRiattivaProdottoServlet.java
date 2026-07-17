package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.ProdottoDao;
import dao.ProdottoDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminRiattivaProdottoServlet")
public class AdminRiattivaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProdotto = Integer.parseInt(request.getParameter("id"));

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);

        try {
            prodottoDao.doRiattiva(idProdotto);
            response.sendRedirect("AdminProdottiServlet?successo=riattivato");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminProdottiServlet?errore=riattivazione");
        }
    }
}