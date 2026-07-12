package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import model.ProdottoBean;
import model.CategoriaBean;

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

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);
        CategoriaDao categoriaDao = new CategoriaDaoImpl(connessioneDB);

        try {

        	List<ProdottoBean> listaProdotti = prodottoDao.doRetrieveAllActive();

            List<CategoriaBean> listaCategorie = categoriaDao.doRetrieveAll();

            request.setAttribute("listaProdotti", listaProdotti);
            request.setAttribute("listaCategorie", listaCategorie);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/catalogo.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace(); 
            response.sendRedirect("index.jsp?errore=catalogo");
        }
    }
}