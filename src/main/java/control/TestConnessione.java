package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestConnessione")
public class TestConnessione extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // 1. Recupera il DataSource configurato in context.xml
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/demarcaf");

            // 2. Ottiene una connessione dal pool
            Connection conn = ds.getConnection();

            // 3. Esegue una query di prova
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM categoria");

            // 4. Stampa i risultati
            out.println("<h1>Connessione riuscita!</h1>");
            out.println("<h2>Categorie nel database:</h2><ul>");
            while (rs.next()) {
                out.println("<li>" + rs.getInt("id_categoria") + " - " + rs.getString("nome") + "</li>");
            }
            out.println("</ul>");

            // 5. Chiude tutto
            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            out.println("<h1>ERRORE di connessione</h1>");
            out.println("<pre>" + e.getMessage() + "</pre>");
            e.printStackTrace();
        }
    }
}