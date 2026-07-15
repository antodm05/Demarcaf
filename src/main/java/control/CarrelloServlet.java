package control;

import java.io.IOException;

import model.Carrello;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        HttpSession sessione = request.getSession(false);

      Carrello carrello = null;
        if (sessione != null) {
            carrello = (Carrello) sessione.getAttribute("carrello");
        }

   
        request.setAttribute("carrello", carrello);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/carrello.jsp");
        dispatcher.forward(request, response);
    }
}