package model;

import java.io.Serializable;


public class ArticoloCarrello implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private ProdottoBean prodotto;

    
    private int quantita;

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

    public double getSubtotale() {
        return prodotto.getPrezzo() * quantita;
    }
}