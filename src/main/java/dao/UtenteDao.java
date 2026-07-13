package dao;

import java.sql.SQLException;
import model.UtenteBean;


public interface UtenteDao {

    void doSave(UtenteBean utente) throws SQLException;

    UtenteBean doRetrieveByEmail(String email) throws SQLException;

}