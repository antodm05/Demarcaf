package dao;

import java.sql.SQLException;
import model.UtenteBean;

//solo le firme dei metodi 

public interface UtenteDao {

    // Salva un nuovo utente nel database (registrazione)
    void doSave(UtenteBean utente) throws SQLException;

    // Cerca un utente tramite email 
    UtenteBean doRetrieveByEmail(String email) throws SQLException;

}