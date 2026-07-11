package dao;

import java.sql.SQLException;
import java.util.List;

import model.OrdineBean;
import model.ArticoloCarrello;
import model.DettaglioOrdineBean;

public interface OrdineDao {

    void doSave(OrdineBean ordine, List<ArticoloCarrello> articoli) throws SQLException;

    List<OrdineBean> doRetrieveByUtente(int idUtente) throws SQLException;

    // Tutti gli ordini di tutti i clienti per l'admin
    List<OrdineBean> doRetrieveAllPerAdmin() throws SQLException;

    List<OrdineBean> doRetrieveByData(String dataInizio, String dataFine) throws SQLException;

    List<OrdineBean> doRetrieveByEmailCliente(String email) throws SQLException;

    List<DettaglioOrdineBean> doRetrieveDettagli(int idOrdine) throws SQLException;

}