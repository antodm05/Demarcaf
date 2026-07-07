package control;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import dao.ProdottoDao;
import dao.ProdottoDaoImpl;
import model.ProdottoBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/AdminAggiornaProdottoServlet")
@MultipartConfig
public class AdminAggiornaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Leggo i dati dal form 
        int idProdotto = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));

        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);

        try {
            // Carico il prodotto attuale, per recuperare la vecchia immagine
            ProdottoBean prodotto = prodottoDao.doRetrieveById(idProdotto);

            //  Gestisco l'immagine: se ne carico una nuova la sostituisco, altrimenti tengo quella vecchia
            
            Part partImmagine = request.getPart("immagine");
            if (partImmagine != null && partImmagine.getSize() > 0) {
                // C'e' una nuova immagine: la salvo con nome univoco
                String nomeOriginale = partImmagine.getSubmittedFileName();
                String estensione = nomeOriginale.substring(nomeOriginale.lastIndexOf("."));
                String nomeFileImmagine = UUID.randomUUID().toString() + estensione;
//cartella originale
                String cartellaUpload = getServletContext().getRealPath("/images");
                Path percorsoFile = Paths.get(cartellaUpload, nomeFileImmagine);
                try (InputStream input = partImmagine.getInputStream()) {
                    Files.copy(input, percorsoFile); //salvo
                }
                // Aggiorno il bean con la nuova immagine
                prodotto.setImmagine(nomeFileImmagine);
            }
            // Se non c'e' nuova immagine, il bean tiene gia' quella vecchia (dal doRetrieveById)

            // Aggiorno gli altri campi con i dati del form
            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita);
            prodotto.setIdCategoria(idCategoria);

            // Salvo le modifiche nel database
            prodottoDao.doUpdate(prodotto);
            response.sendRedirect("AdminProdottiServlet?successo=modificato");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminProdottiServlet?errore=aggiornamento");
        }
    }
}