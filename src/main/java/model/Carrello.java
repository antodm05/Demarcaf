package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//  carrello Contiene la lista degli articoli e i metodi per gestirli.
public class Carrello implements Serializable {

    private static final long serialVersionUID = 1L;

    // La lista degli articoli nel carrello 
    private List<ArticoloCarrello> articoli;

    // Costruttore:la lista parte vuota
    public Carrello() {
        this.articoli = new ArrayList<ArticoloCarrello>();
    }

    // return tutti gli articoli lo usera la JSP per mostrarli
    public List<ArticoloCarrello> getArticoli() {
        return articoli;
    }
//----------------------------------------------------------------------------
    
    public void aggiungiProdotto(ProdottoBean prodotto, int quantita) {

        // Verifico se gia sta nel carrello
        for (ArticoloCarrello articolo : articoli) {
            if (articolo.getProdotto().getIdProdotto() == prodotto.getIdProdotto()) {
                // Gia' presente aumento la quantita 
                articolo.setQuantita(articolo.getQuantita() + quantita);
                return;
            }
        }

        // aggiungo un nuovo articolo
        articoli.add(new ArticoloCarrello(prodotto, quantita));
    }
    
    //-------------------------------------------------------------------

    // dato il suo id
    public void rimuoviProdotto(int idProdotto) {
        // verifichiamo se rispetta ID 
        articoli.removeIf(articolo -> articolo.getProdotto().getIdProdotto() == idProdotto);
    }

    // Svuota tutto il carrello , tipo dopo l'acquisto 
    public void svuota() {
        articoli.clear();
    }

    // somma dei subtotali di ogni articolo
    public double getTotale() {
        double totale = 0;
        for (ArticoloCarrello articolo : articoli) {
            totale = totale + articolo.getSubtotale();
        }
        return totale;
    }

    //  contatore del carrello 
    public int getNumeroArticoli() {
        return articoli.size();
    }
}