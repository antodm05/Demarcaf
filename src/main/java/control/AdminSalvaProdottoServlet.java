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

@WebServlet("/AdminSalvaProdottoServlet")
@MultipartConfig
public class AdminSalvaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Leggo i dati testuali dal form
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));

        // Gestisco l'upload dell'immagine
        String nomeFileImmagine = null;
        Part partImmagine = request.getPart("immagine"); // Recupero il file vero e proprio

        if (partImmagine != null && partImmagine.getSize() > 0) {
        	
            // Prendo il nome originale del file caricato
            String nomeOriginale = partImmagine.getSubmittedFileName();
            
            // Ricavo l'estensione 
            String estensione = nomeOriginale.substring(nomeOriginale.lastIndexOf("."));
            
            // Genero un nome UNIVOCO uso l'UUID  casuale per garantire nomi di file univoci ed evitare collisioni
            nomeFileImmagine = UUID.randomUUID().toString() + estensione;

            // Cartella dove salvo fisicamente le immagini
            String cartellaUpload = getServletContext().getRealPath("/images");
            Path percorsoFile = Paths.get(cartellaUpload, nomeFileImmagine);

            // Scrivo il file sul disco
            try (InputStream input = partImmagine.getInputStream()) {
                Files.copy(input, percorsoFile); // scrivo fisicamente il file sul disco
            }
        }

        // Creo il bean prodotto e lo riempio
        ProdottoBean prodotto = new ProdottoBean();
        prodotto.setNome(nome);
        prodotto.setDescrizione(descrizione);
        prodotto.setPrezzo(prezzo);
        prodotto.setQuantita(quantita);
        prodotto.setIdCategoria(idCategoria);
        prodotto.setImmagine(nomeFileImmagine); // salvo solo il NOME del file, non il file

        //  Salvo il prodotto nel database tramite il DAO
        DataSource connessioneDB = (DataSource) getServletContext().getAttribute("DataSource");
        ProdottoDao prodottoDao = new ProdottoDaoImpl(connessioneDB);

        try {
            prodottoDao.doSave(prodotto);

            response.sendRedirect("AdminProdottiServlet?successo=aggiunto");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminProdottiServlet?errore=salvataggio");
        }
    }
}