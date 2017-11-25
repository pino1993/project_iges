package com.prezzipazzi.bean;
/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class PrestazioniOpera extends Offerte{
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore che eredita l'id ed il prezzo dalla classe Offerte ed aggiunge altre caratteristiche.
	 * @param id
	 * @param prezzo
	 * @param localita
	 * @param descrizione
	 * @param fornitore
	 * @param giudizio
	 */
	public PrestazioniOpera(int id, double prezzo, String localita, String descrizione, String fornitore,int giudizio) {
		super(id, prezzo);
		this.descrizione = descrizione;
		this.fornitore = fornitore;
		this.giudizio = giudizio;
		this.localita = localita;
		this.giudizio = giudizio;
	}
	
	/**
	 * In questo caso, il prodotto e' sempre acquistabile
	 */
	public boolean eAcquistabile() {
		return true;
	}
	
	/**
	 * In questo caso, i prodotti non sono scontati
	 */
	public boolean sconti() {
		return false;
	}
	
	/**
	 * @return localita
	 */
	public String getLocalita() {
		return localita;
	}
	
	/**
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * @return fornitore
	 */
	public String getFornitore() {
		return fornitore;
	}
	
	/**
	 * @return giudizio
	 */
	public int getGiudizio() {
		return giudizio;
	}

	public int compareTo(Offerte arg0) {
		return 0;
	}
	
	/**
	 * Sovrascrive toString() di Offerte
	 */
	public String toString() {
		return super.toString() + " [Luogo: " + localita+"]" + " [Descrizione: " + descrizione+"]" + " [Fornitore: " + fornitore+"]" + " [Feedback: " + giudizio + "]";
	}

	/**
	 * Sovrascrive equals() di Offerte
	 */
	public boolean equals(Object otherObject) {
		PrestazioniOpera p; 
		if (!(super.equals(otherObject))){
			return false;
		}
		p = (PrestazioniOpera) otherObject;
		return localita.equalsIgnoreCase(p.getLocalita()) && descrizione.equalsIgnoreCase(p.descrizione) && fornitore.equalsIgnoreCase(p.fornitore) && giudizio == p.getGiudizio();
	}
	
	/**
	 * Sovrascrive clone() di Offerte
	 */
	public Object clone() {
	
		return super.clone();
	}
	
	//Variabili
	private String localita,descrizione,fornitore;
	private int giudizio;
}
