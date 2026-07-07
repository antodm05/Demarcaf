package dao;

import java.sql.SQLException;
import java.util.List;

import model.OrdineBean;
import model.ArticoloCarrello;

public interface OrdineDao {

    // Salva un ordine completo: l'ordine + dettagli Riceve l'ordine (con i dati di spedizione) e gli articoli del carrello.
    void doSave(OrdineBean ordine, List<ArticoloCarrello> articoli) throws SQLException;

}