package dao;

import java.sql.SQLException;
import java.util.List;
import model.ProdottoBean;

public interface ProdottoDao 
{

    List<ProdottoBean> doRetrieveAllActive() throws SQLException;

    
    List<ProdottoBean> doRetrieveAll() throws SQLException;
    
    void doSave(ProdottoBean prodotto) throws SQLException;
    
    void doSoftDelete(int idProdotto) throws SQLException;
    
    
    ProdottoBean doRetrieveById(int idProdotto) throws SQLException;
    
    
    void doUpdate(ProdottoBean prodotto) throws SQLException;
    
    void doRiattiva(int idProdotto) throws SQLException;
    
}