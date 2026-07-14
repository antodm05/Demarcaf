package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.UtenteBean;


public class UtenteDaoImpl implements UtenteDao {
	
    private DataSource ds;

    public UtenteDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void doSave(UtenteBean utente) throws SQLException 
    {
        String sql = "INSERT INTO utente (email, password, ruolo, nome, cognome) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, utente.getEmail());
            ps.setString(2, utils.SecurityUtils.toDigest(utente.getPassword()));
            ps.setString(3, utente.getRuolo());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
            ps.executeUpdate();
        }
    }
    //-----------------------------------------------------------------------------------
    @Override
    public UtenteBean doRetrieveByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM utente WHERE email = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
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