package dao;

import java.sql.SQLException;
import java.util.List;
import model.CategoriaBean;

public interface CategoriaDao {

    // Restituisce tutte le categorie 

    List<CategoriaBean> doRetrieveAll() throws SQLException;

}