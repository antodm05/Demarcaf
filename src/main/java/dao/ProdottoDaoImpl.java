package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.ProdottoBean;

public class ProdottoDaoImpl implements ProdottoDao {

    // apre pool di connessioni
    private DataSource connessioneDB;

    public ProdottoDaoImpl(DataSource ds) {
        this.connessioneDB = ds;
    }

    @Override
    public List<ProdottoBean> doRetrieveAllActive() throws SQLException {

        // lista vuota 
        List<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();

        // solo prodotti attivi, non cancellati
        String sql = "SELECT * FROM prodotto WHERE attivo = true";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {

                // Scorro tutte le righe 
                while (rs.next()) {

                    // Per ogni riga creo un bean , riempio con i dati dal DB
                    ProdottoBean prodotto = new ProdottoBean();
                    prodotto.setIdProdotto(rs.getInt("id_prodotto"));
                    prodotto.setNome(rs.getString("nome"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    prodotto.setImmagine(rs.getString("immagine"));
                    prodotto.setAttivo(rs.getBoolean("attivo"));
                    prodotto.setIdCategoria(rs.getInt("id_categoria"));

                    listaProdotti.add(prodotto);
                }
            }
        }

        return listaProdotti;
    }
}