package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Carrello implements Serializable {

    private static final long serialVersionUID = 1L;

     
    private List<ArticoloCarrello> articoli;

    public Carrello() {
        this.articoli = new ArrayList<ArticoloCarrello>();
    }

    public List<ArticoloCarrello> getArticoli() {
        return articoli;
    }
//----------------------------------------------------------------------------
    
    public void aggiungiProdotto(ProdottoBean prodotto, int quantita) {

        for (ArticoloCarrello articolo : articoli) {
            if (articolo.getProdotto().getIdProdotto() == prodotto.getIdProdotto()) {
                // aumento la quantita 
                articolo.setQuantita(articolo.getQuantita() + quantita);
                return;
            }
        }

        
        articoli.add(new ArticoloCarrello(prodotto, quantita));
    }
    
    //-------------------------------------------------------------------

  
    public void rimuoviProdotto(int idProdotto) {
        articoli.removeIf(articolo -> articolo.getProdotto().getIdProdotto() == idProdotto);
    }
    
    
  //-------------------------------------------------------------------

    public void aggiornaQuantita(int idProdotto, int nuovaQuantita) {
        for (ArticoloCarrello articolo : articoli) {
            if (articolo.getProdotto().getIdProdotto() == idProdotto) {
                if (nuovaQuantita <= 0) {

                	rimuoviProdotto(idProdotto);
                } else {
                    articolo.setQuantita(nuovaQuantita);
                }
                return;
            }
        }
    }

    public void svuota() {
        articoli.clear();
    }

    public double getTotale() {
        double totale = 0;
        for (ArticoloCarrello articolo : articoli) {
            totale = totale + articolo.getSubtotale();
        }
        return totale;
    }

    public int getNumeroArticoli() {
        return articoli.size();
    }
}



