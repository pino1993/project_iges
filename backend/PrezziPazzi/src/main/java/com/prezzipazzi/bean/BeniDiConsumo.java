package com.prezzipazzi.bean;

/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class BeniDiConsumo extends Offerte{
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore che eredita l'id ed il prezzo dalla classe Offerte ed aggiunge altre caratteristiche.
	 * @param id
	 * @param prezzo
	 * @param descrizione
	 * @param daVendere
	 * @param fornitore
	 * @param giudizio
	 */
	public BeniDiConsumo(int id, double prezzo,String img,String descrizione,int daVendere,String fornitore, int giudizio) {
		super(id, prezzo,img);
		this.daVendere = daVendere;
		this.descrizione = descrizione;
		this.fornitore = fornitore;
		this.giudizio = giudizio;
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

	/**
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @return prodottiDaVendere
	 */
	public int getDaVendere() {
		return daVendere;
	}
	/**
	 * Metodo che consente di aggiornare il numero di prodotti disponibili, ricevendo in ingresso un intero rappresentate il 
	 * numero di prodotti venduti.
	 * @param quantita
	 */
	public void setDaVendere(int quantita) {
		daVendere -= quantita;
	}

	/**
	 * Metodo che controlla se il prodotto risulta essere ancora acquistabile nel catalogo.
	 */
	public boolean eAcquistabile() {
		if (daVendere > 0)
			return true;
		else
			return false;
	}

	/**
	 * In questo caso, non si devono applicare sconti
	 * @return false
	 */
	public boolean sconti() {
		return false;
	}

	/**
	 * Sovrascrive il metodo toString() di Offerte
	 */
	public String toString(){
		return super.toString() + " [Disponibilita: " + daVendere +"]" + " [Tipologia: " + descrizione+ "]" + " [Fornitore: " + fornitore +"]"+ " [Feedback: "+ giudizio +"]";
	}

	/**
	 * Sovrascrivere il metodo equals() di Offerte
	 */
	public boolean equals(Object otherObject) {

		BeniDiConsumo b;
		if (!(super.equals(otherObject))) {
			return false;
		}
		b = (BeniDiConsumo) otherObject;
		return daVendere == b.getDaVendere() && descrizione.equalsIgnoreCase(b.getDescrizione()) && giudizio == b.getGiudizio() && fornitore.equalsIgnoreCase(getFornitore());
	}

	/**
	 * Sovrascrivere il metodo clone() di Offerte
	 */
	public Object clone() {

		return super.clone();
	}

	public int compareTo(Offerte arg0) {
		return 0;
	}

	//Variabili
	private String descrizione,fornitore;
	private int daVendere,giudizio;
}
