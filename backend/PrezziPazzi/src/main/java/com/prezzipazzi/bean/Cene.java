package com.prezzipazzi.bean;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class Cene extends Offerte  {
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore che eredita l'id ed il prezzo dalla classe Offerte ed aggiunge altre caratteristiche.
	 * @param id
	 * @param prezzo
	 * @param ristorante
	 * @param descrizione
	 * @param luogo
	 * @param scadenza
	 * @param daVendere
	 */
	public Cene(int id, double prezzo, String img,String ristorante,String descrizione,String luogo,GregorianCalendar scadenza,int daVendere) {
		super(id, prezzo,img);
		this.daVendere = daVendere;
		this.descrizione = descrizione;
		this.luogo = luogo;
		this.ristorante = ristorante;
		this.scadenza = scadenza;
	}

	/**
	 * Controlla se l'offerta puo essere aggiunta al catalogo 
	 */
	public boolean eAcquistabile() {
		if(scadenza.before(new GregorianCalendar()) || daVendere <= 0 )
			return false;
		else
			return true;
	}

	/**
	 * @return ristorante
	 */
	public String getRistorante() {
		return ristorante;
	}
	/**
	 * @return luogo
	 */
	public String getLuogo() {
		return luogo;
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
	 * @return scadenza
	 */
	public GregorianCalendar getScadenza() {
		return scadenza;
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
	 * Sovrascrive il metodo toString() di Offerte
	 */
	public String toString() {
		int giornoS = scadenza.get(Calendar.DAY_OF_MONTH);
		int meseS = scadenza.get(Calendar.MONTH) + 1;
		int annoS = scadenza.get(Calendar.YEAR);
		return super.toString() + " [Ristorante: " + ristorante + "]" + " [Luogo: " + luogo +"]" + " [Disponibilita: " + daVendere +"]"+ " [Scadenza: " + giornoS + "/" + meseS + "/" + annoS + "]" +"\n" + "Menu: "+ descrizione;
	}

	/**
	 * Sovrascrive il metodo equals() di Offerte
	 */
	public boolean equals (Object otherObject) {

		Cene c;

		if(!(super.equals(otherObject))) {
			return false;
		}

		c = (Cene) otherObject;
		return ristorante.equalsIgnoreCase(c.getRistorante()) && luogo.equalsIgnoreCase(c.getLuogo()) && descrizione.equalsIgnoreCase(c.getDescrizione()) && scadenza == c.getScadenza() && daVendere == c.getDaVendere(); 
	}
	/**
	 * Sovrascrive il metodo clone() di Offerte
	 */
	public Object clone() {
		return super.clone();
	}

	public int compareTo(Offerte arg0) {
		return 0;
	}

	//Variabili
	private String ristorante,luogo,descrizione;
	private int daVendere;
	private GregorianCalendar scadenza;

}
