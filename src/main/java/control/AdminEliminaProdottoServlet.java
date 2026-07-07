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

@WebServlet("/AdminEliminaProdottoServlet")
public class AdminEliminaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) //oggetti request e response
            throws ServletException, IOException {

        // Leggo l'id del prodotto  dal link ?id=
        int idProdotto = Integer.parseInt(request.getParameter("id"));

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);

        try {
            // Disattivo il prodotto
            prodottoDao.doSoftDelete(idProdotto);
            // elenco prodotti
            response.sendRedirect("AdminProdottiServlet?successo=eliminato");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminProdottiServlet?errore=eliminazione");
        }
    }
}