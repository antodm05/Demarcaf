package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.ArticoloCarrello;
import model.OrdineBean;

public class OrdineDaoImpl implements OrdineDao {

    private DataSource connessioneDB;

    public OrdineDaoImpl(DataSource ds) {
        this.connessioneDB = ds;
    }
//---------------------------------------------------------------------
    @Override
    public void doSave(OrdineBean ordine, List<ArticoloCarrello> articoli) throws SQLException {

        // var per la connessione
        Connection conn = null;

        try {
            conn = connessioneDB.getConnection();//  Apro la connessione al database dal pool

            //  disabilito il salvataggio automatico pk voglio che si salvi tutto (ordine + righe) come blocco unico senza dover fare auto salvare le query
            conn.setAutoCommit(false);

            // salvo l'ordine principale con NOW per prendere la data 
            String sqlOrdine = "INSERT INTO ordine (data, totale, indirizzo, citta, cap, "
                    + "provincia, metodo_pagamento, stato, id_utente) "
                    + "VALUES (NOW(), ?, ?, ?, ?, ?, ?, ?, ?)";

            // Statement.RETURN_GENERATED_KEYS: mi faccio restituire l'id per poter identificare l'ordine e collegarci le righe gli sto chiedendo di ricordarlo
            PreparedStatement psOrdine = conn.prepareStatement(sqlOrdine, Statement.RETURN_GENERATED_KEYS);
            psOrdine.setDouble(1, ordine.getTotale()); //valore dell'oggetto
            psOrdine.setString(2, ordine.getIndirizzo()); //riempio i ? dell 'ordine ed eseguo INSERT
            psOrdine.setString(3, ordine.getCitta());
            psOrdine.setString(4, ordine.getCap());
            psOrdine.setString(5, ordine.getProvincia());
            psOrdine.setString(6, ordine.getMetodoPagamento());
            psOrdine.setString(7, "in elaborazione"); // per lo stato
            psOrdine.setInt(8, ordine.getIdUtente());
            psOrdine.executeUpdate();

            // Recupero l'id dell'ordine appena creato 
            int idOrdineGenerato = 0;
            try (ResultSet rs = psOrdine.getGeneratedKeys()) {
                if (rs.next()) {
                    idOrdineGenerato = rs.getInt(1); // prendo solo il primo che è stato generato
                }
            }
            psOrdine.close();

            //  dopo aver preso id ordine e aver riempito i campi salvo ogni riga in dettaglio 
            String sqlDettaglio = "INSERT INTO dettaglio_ordine (id_ordine, id_prodotto, "
                    + "quantita, prezzo_unitario) VALUES (?, ?, ?, ?)";
            PreparedStatement psDettaglio = conn.prepareStatement(sqlDettaglio);

            // Per ogni articolo del carrello creo una riga
            for (ArticoloCarrello articolo : articoli) {
                psDettaglio.setInt(1, idOrdineGenerato); // collego questa riga all'ordine 
                psDettaglio.setInt(2, articolo.getProdotto().getIdProdotto());
                psDettaglio.setInt(3, articolo.getQuantita());
                
                // copio il prezzo ATTUALE del prodotto  Cosi se il prezzo cambiera l'ordine mantiene questo 
                
                psDettaglio.setDouble(4, articolo.getProdotto().getPrezzo());
                psDettaglio.addBatch(); // accumulo le righe cosi le eseguo tutte insieme 
            }
            psDettaglio.executeBatch(); // eseguo tutte le righe insieme
            psDettaglio.close();

            // TUTTO OK: confermo la transazione (salva davvero tutto)
            conn.commit();

        } catch (SQLException e) {
            // ERRORE: annullo tutto cosi' non resta un ordine a meta usando rollback. ( basta un errore)
            if (conn != null) {
                conn.rollback();
            }
            throw e; // rilancio l'errore 

        } finally {
            // di base  Riporto la connessione allo stato normale e la chiudo
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    
    
//-------------------------------------------------------------------------
    
    @Override
    public List<OrdineBean> doRetrieveByUtente(int idUtente) throws SQLException {

        // Lista che conterra' gli ordini trovati
        List<OrdineBean> listaOrdini = new ArrayList<OrdineBean>();

        // Cerco gli ordini di questo utente, [dal piu recente al piu vecchio] FILTRO PER ID COSI OGNI UTENTE VEDE IL PROPRIO STORICO 
        String sql = "SELECT * FROM ordine WHERE id_utente = ? ORDER BY data DESC";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUtente);

            try (ResultSet rs = ps.executeQuery()) {
                // Ciclo su tutti gli ordini trovati 
                while (rs.next()) {
                    OrdineBean ordine = new OrdineBean();
                    ordine.setIdOrdine(rs.getInt("id_ordine"));
                    ordine.setData(rs.getTimestamp("data"));
                    ordine.setTotale(rs.getDouble("totale"));
                    ordine.setIndirizzo(rs.getString("indirizzo"));
                    ordine.setCitta(rs.getString("citta"));
                    ordine.setCap(rs.getString("cap"));
                    ordine.setProvincia(rs.getString("provincia"));
                    ordine.setMetodoPagamento(rs.getString("metodo_pagamento"));
                    ordine.setStato(rs.getString("stato"));
                    ordine.setIdUtente(rs.getInt("id_utente"));
                    listaOrdini.add(ordine);
                }
            }
        }
        return listaOrdini;
    }
    
    //--------------------------------------------------------------------------------
    // TUTTI GLI ORDINI per l'admin senza filtri
    @Override
    public List<OrdineBean> doRetrieveAllPerAdmin() throws SQLException {

        List<OrdineBean> listaOrdini = new ArrayList<OrdineBean>();

        // Prendo tutti gli ordini + l'email del cliente con la JOIN tramite id_utente, cosi' posso avere anche l'email del cliente.

        String sql = "SELECT ordine.*, utente.email AS email_cliente FROM ordine "
                   + "JOIN utente ON ordine.id_utente = utente.id_utente " // cosi per ogni ordine ho anche i dati del cliente che l'ha fatto
                   + "ORDER BY ordine.data DESC";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listaOrdini.add(estraiOrdine(rs));
            }
        }
        return listaOrdini;
    }

//----------------------------------------------------------------------------
    
    //  ORDINI PER INTERVALLO DI DATE
    @Override
    public List<OrdineBean> doRetrieveByData(String dataInizio, String dataFine) throws SQLException {

        List<OrdineBean> listaOrdini = new ArrayList<OrdineBean>();

       
        // dalla data di inizio in poi e fino alla data di fine
        
        String sql = "SELECT ordine.*, utente.email AS email_cliente FROM ordine "
                   + "JOIN utente ON ordine.id_utente = utente.id_utente "
                   + "WHERE ordine.data >= ? AND ordine.data <= ? "
                   + "ORDER BY ordine.data DESC";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

      
            ps.setString(1, dataInizio + " 00:00:00"); // sostituisco i ?
            ps.setString(2, dataFine + " 23:59:59");

            try (ResultSet rs = ps.executeQuery()) {
            	
                while (rs.next()) {
                    listaOrdini.add(estraiOrdine(rs));
                }
            }
        }
        return listaOrdini;
    }


    // --------------------------------------------------------------
    //  ORDINI DI UN CLIENTE cercato per email
    @Override
    public List<OrdineBean> doRetrieveByEmailCliente(String email) throws SQLException {

        List<OrdineBean> listaOrdini = new ArrayList<OrdineBean>();

    
        // LIKE % fa una ricerca PARZIALE non serve scrivere l'email esatta
        String sql = "SELECT ordine.*, utente.email AS email_cliente FROM ordine "
                   + "JOIN utente ON ordine.id_utente = utente.id_utente "
                   + "WHERE utente.email LIKE ? "
                   + "ORDER BY ordine.data DESC";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + email + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaOrdini.add(estraiOrdine(rs));
                }
            }
        }
        return listaOrdini;
    }


    // -------------------------------------------------------------------
    // trasforma una riga in un OrdineBean Lo uso in tutti e 3 i metodi
    private OrdineBean estraiOrdine(ResultSet rs) throws SQLException {
        OrdineBean ordine = new OrdineBean();
        ordine.setIdOrdine(rs.getInt("id_ordine"));
        ordine.setData(rs.getTimestamp("data"));
        ordine.setTotale(rs.getDouble("totale"));
        ordine.setIndirizzo(rs.getString("indirizzo"));
        ordine.setCitta(rs.getString("citta"));
        ordine.setStato(rs.getString("stato"));
        ordine.setIdUtente(rs.getInt("id_utente"));
        ordine.setEmailCliente(rs.getString("email_cliente"));
        return ordine;
    }
   
    
}