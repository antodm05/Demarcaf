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

    private DataSource connessioneDB;

    public ProdottoDaoImpl(DataSource ds) {
        this.connessioneDB = ds;
    }

    @Override
    public List<ProdottoBean> doRetrieveAllActive() throws SQLException {

         
        List<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();

        String sql = "SELECT * FROM prodotto WHERE attivo = true";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

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
    
    //---------------------------------------------------
    
    @Override
    public List<ProdottoBean> doRetrieveAll() throws SQLException {

        List<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();

        String sql = "SELECT * FROM prodotto";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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
    //-----------------------------------------------------
    
    @Override
    public void doSave(ProdottoBean prodotto) throws SQLException {

       
        String sql = "INSERT INTO prodotto (nome, descrizione, prezzo, quantita, immagine, id_categoria) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

           
            ps.setString(1, prodotto.getNome());
            ps.setString(2, prodotto.getDescrizione());
            ps.setDouble(3, prodotto.getPrezzo());
            ps.setInt(4, prodotto.getQuantita());
            ps.setString(5, prodotto.getImmagine());
            ps.setInt(6, prodotto.getIdCategoria());

            ps.executeUpdate(); // per INSERT
        }
    }
    
    //---------------------------------------------------
    
    @Override
    public void doSoftDelete(int idProdotto) throws SQLException {

        String sql = "UPDATE prodotto SET attivo = false WHERE id_prodotto = ?";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProdotto); 
            ps.executeUpdate(); 
        }
    }
    
    
    @Override
    public ProdottoBean doRetrieveById(int idProdotto) throws SQLException {

        ProdottoBean prodotto = null;
        String sql = "SELECT * FROM prodotto WHERE id_prodotto = ?";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProdotto); 

            try (ResultSet rs = ps.executeQuery()) {
           if (rs.next()) {
                    prodotto = new ProdottoBean();
                    prodotto.setIdProdotto(rs.getInt("id_prodotto"));
                    prodotto.setNome(rs.getString("nome"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    prodotto.setImmagine(rs.getString("immagine"));
                    prodotto.setAttivo(rs.getBoolean("attivo"));
                    prodotto.setIdCategoria(rs.getInt("id_categoria"));
                }
            }
        }
        return prodotto;
    }
    
    //------------------------------------------------------
    
    @Override
    public void doUpdate(ProdottoBean prodotto) throws SQLException {

        String sql = "UPDATE prodotto SET nome = ?, descrizione = ?, prezzo = ?, "
                   + "quantita = ?, immagine = ?, id_categoria = ? WHERE id_prodotto = ?";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prodotto.getNome());
            ps.setString(2, prodotto.getDescrizione());
            ps.setDouble(3, prodotto.getPrezzo());
            ps.setInt(4, prodotto.getQuantita());
            ps.setString(5, prodotto.getImmagine());
            ps.setInt(6, prodotto.getIdCategoria());
            ps.setInt(7, prodotto.getIdProdotto()); 

            ps.executeUpdate();
        }
    }
    
    
    //-------------------------------------------------------------
    @Override
    public void doRiattiva(int idProdotto) throws SQLException {

        String sql = "UPDATE prodotto SET attivo = true WHERE id_prodotto = ?";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProdotto);
            ps.executeUpdate();
        }
    }
    
}