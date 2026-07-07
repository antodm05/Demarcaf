package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.UtenteBean;

//implemento l'interfaccia 

public class UtenteDaoImpl implements UtenteDao {
	
    private DataSource ds;

    public UtenteDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void doSave(UtenteBean utente) throws SQLException 
    {
    	//non inserimao i valori direttamente ma li inseriamo dopo e poi ci protegge dai SQL INJECTION
        String sql = "INSERT INTO utente (email, password, ruolo, nome, cognome) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, utente.getEmail());
            //richiamo la funzione di protezione per la password
            ps.setString(2, utils.SecurityUtils.toDigest(utente.getPassword()));
            ps.setString(3, utente.getRuolo());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
//esegue insert 
            ps.executeUpdate();
        }
    }
    //-----------------------------------------------------------------------------------
    @Override
    public UtenteBean doRetrieveByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM utente WHERE email = ?";

        // statement si chiudono da solo alla fine
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            // executeQuery perche  e una SELECT per leggere e non modificare
            try (ResultSet rs = ps.executeQuery()) {

                // Se rs.next() e' vero, significa che ho trovato un utente
                if (rs.next()) {
                    // Creo un oggetto bean vuoto e lo riempio con i dati dal DB
                    UtenteBean utente = new UtenteBean();
                    utente.setIdUtente(rs.getInt("id_utente"));
                    utente.setEmail(rs.getString("email"));
                    utente.setPassword(rs.getString("password"));
                    utente.setRuolo(rs.getString("ruolo"));
                    utente.setNome(rs.getString("nome"));
                    utente.setCognome(rs.getString("cognome"));

                    return utente;
                }
            }
        }

        return null;
    }
    
    
}