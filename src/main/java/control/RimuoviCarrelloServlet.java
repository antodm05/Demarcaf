package control;

import java.io.IOException;

import model.Carrello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

 
@WebServlet("/RimuoviCarrelloServlet")
public class RimuoviCarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // id
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

       
        HttpSession sessione = request.getSession(false);

        // se ce sessione e carrello rimuovo
        if (sessione != null) {
            Carrello carrello = (Carrello) sessione.getAttribute("carrello");
            if (carrello != null) {
            	
                carrello.rimuoviProdotto(idProdotto);
            }
        }

        
        response.sendRedirect("CarrelloServlet");
    }
}