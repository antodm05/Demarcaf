package control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// risposta in JSON con i comuni 
@WebServlet("/ComuniServlet")
public class ComuniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Leggo 
    	
        String provincia = request.getParameter("provincia");

        // dico al browser di JSON cosi sa cosa aspettarsi
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // Costruisco la risposta JSON in base alla provincia 
        String json;

        if ("SA".equalsIgnoreCase(provincia)) {
            json = "[\"Salerno\",\"Cava de' Tirreni\",\"Battipaglia\",\"Nocera Inferiore\"]";
        } else if ("NA".equalsIgnoreCase(provincia)) {
            json = "[\"Napoli\",\"Pozzuoli\",\"Torre del Greco\",\"Giugliano\"]";
        } else if ("AV".equalsIgnoreCase(provincia)) {
            json = "[\"Avellino\",\"Ariano Irpino\",\"Montella\"]";
        } else {
            // array vuoto
            json = "[]";
        }

        // Stampo il JSON come risposta
        out.print(json);
        out.flush();
    }
}