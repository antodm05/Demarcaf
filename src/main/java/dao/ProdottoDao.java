package dao;

import java.sql.SQLException;
import java.util.List;
import model.ProdottoBean;

public interface ProdottoDao 
{

    //  lista di tutti i prodotti attivi quelli che hanno la fleg a true 
    List<ProdottoBean> doRetrieveAllActive() throws SQLException;

    
 // Restituisce tutti i prodotti, anche quelli disattivati solo per l'admin
    List<ProdottoBean> doRetrieveAll() throws SQLException;
    
 // Salva un nuovo prodotto nel database
    void doSave(ProdottoBean prodotto) throws SQLException;
    
}