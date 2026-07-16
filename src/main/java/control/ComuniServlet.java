package control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ComuniServlet")
public class ComuniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
        String provincia = request.getParameter("provincia");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String json;

        if ("SA".equalsIgnoreCase(provincia)) {
            json = "[\"Salerno\",\"Cava de' Tirreni\",\"Battipaglia\",\"Nocera Inferiore\"]";
        } else if ("NA".equalsIgnoreCase(provincia)) {
            json = "[\"Napoli\",\"Pozzuoli\",\"Torre del Greco\",\"Giugliano\"]";
        } else if ("AV".equalsIgnoreCase(provincia)) {
            json = "[\"Avellino\",\"Ariano Irpino\",\"Montella\"]";
        } else {
            json = "[]";
        }

        out.print(json);
        out.flush();
    }
}