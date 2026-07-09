package model;

import java.io.Serializable;


//  verra messo in sessione.
public class ArticoloCarrello implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private ProdottoBean prodotto;

    
    private int quantita;

    // Costruttore vuoto
    public ArticoloCarrello() {
    }

   
    public ArticoloCarrello(ProdottoBean prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public ProdottoBean getProdotto() {
        return prodotto;
    }

    public void setProdotto(ProdottoBean prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    //   totale parziale.
    public double getSubtotale() {
        return prodotto.getPrezzo() * quantita;
    }
}