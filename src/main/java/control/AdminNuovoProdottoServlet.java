package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import model.CategoriaBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminNuovoProdottoServlet")
public class AdminNuovoProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        CategoriaDao categoriaDao = new CategoriaDaoImpl(connessioneDB);

        try {
            //riempire il menu a tendina del form
            List<CategoriaBean> listaCategorie = categoriaDao.doRetrieveAll();
            request.setAttribute("listaCategorie", listaCategorie);

            // richiamo la view
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/nuovoProdotto.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminProdottiServlet?errore=form");
        }
    }
}