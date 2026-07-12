package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdineBean implements Serializable {

    private int idOrdine;
    private Timestamp data;
    private double totale;
    private String indirizzo;
    private String citta;
    private String cap;
    private String provincia;
    private String metodoPagamento;
    private String stato;
    private int idUtente;
    private String emailCliente;
    private String note;

    public OrdineBean() {

    }

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	public String getEmailCliente() {
	    return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
	    this.emailCliente = emailCliente;
	}
    
	public String getNote() {
	    return note;
	}
	public void setNote(String note) {
	    this.note = note;
	}
    

}