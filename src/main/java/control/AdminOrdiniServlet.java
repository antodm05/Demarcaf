package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.OrdineDao;
import dao.OrdineDaoImpl;
import model.OrdineBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminOrdiniServlet")
public class AdminOrdiniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dataInizio = request.getParameter("dataInizio");
        String dataFine = request.getParameter("dataFine");
        String email = request.getParameter("email");

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        OrdineDao ordineDao = new OrdineDaoImpl(connessioneDB);

        try {
            List<OrdineBean> listaOrdini;


            
            if (dataInizio != null && !dataInizio.isEmpty()
                    && dataFine != null && !dataFine.isEmpty()) {
                listaOrdini = ordineDao.doRetrieveByData(dataInizio, dataFine);

                
            } else if (email != null && !email.isEmpty()) {
                listaOrdini = ordineDao.doRetrieveByEmailCliente(email);

                
            } else {
                listaOrdini = ordineDao.doRetrieveAllPerAdmin();
            }

            request.setAttribute("listaOrdini", listaOrdini);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/gestioneOrdini.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?errore=adminOrdini");
        }
    }
}