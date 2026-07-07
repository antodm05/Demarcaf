package model;

import java.io.Serializable;

//  prodotto + quanti pezzi ne voglio.
//  verra messo in sessione.
public class ArticoloCarrello implements Serializable {

    private static final long serialVersionUID = 1L;

    // Il prodotto scelto (riuso il ProdottoBean che ho gia')
    private ProdottoBean prodotto;

    // Quanti pezzi di questo prodotto l'utente vuole
    private int quantita;

    // Costruttore vuoto
    public ArticoloCarrello() {
    }

    // Costruttore comodo: crea l'articolo passando subito prodotto e quantita
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

    // Metodo comodo: calcola il costo di questa riga (prezzo x quantita) per mostrare il totale parziale.
    public double getSubtotale() {
        return prodotto.getPrezzo() * quantita;
    }
}