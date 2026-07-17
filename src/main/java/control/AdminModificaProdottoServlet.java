package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import model.CategoriaBean;
import model.ProdottoBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminModificaProdottoServlet")
public class AdminModificaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProdotto = Integer.parseInt(request.getParameter("id"));

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);
        CategoriaDao categoriaDao = new CategoriaDaoImpl(connessioneDB);

        try {
            ProdottoBean prodotto = prodottoDao.doRetrieveById(idProdotto);
            request.setAttribute("prodotto", prodotto);

            List<CategoriaBean> listaCategorie = categoriaDao.doRetrieveAll();
            request.setAttribute("listaCategorie", listaCategorie);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/modificaProdotto.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminProdottiServlet?errore=modifica");
        }
    }
}