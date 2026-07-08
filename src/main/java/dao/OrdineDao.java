package dao;

import java.sql.SQLException;
import java.util.List;

import model.OrdineBean;
import model.ArticoloCarrello;

public interface OrdineDao {

    // Salva un ordine completo: l'ordine + dettagli Riceve l'ordine (con i dati di spedizione) e gli articoli del carrello.
    void doSave(OrdineBean ordine, List<ArticoloCarrello> articoli) throws SQLException;

    
    
 // Restituisce tutti gli ordini di un utente lo storico
    List<OrdineBean> doRetrieveByUtente(int idUtente) throws SQLException;
    
 // Tutti gli ordini di tutti i clienti per l'admin
    List<OrdineBean> doRetrieveAllPerAdmin() throws SQLException;

    // Ordini filtrati per intervallo di date 
    List<OrdineBean> doRetrieveByData(String dataInizio, String dataFine) throws SQLException;

    // Ordini di un cliente cercato per email
    List<OrdineBean> doRetrieveByEmailCliente(String email) throws SQLException;
    
    
}