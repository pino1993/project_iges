package com.prezzipazzi.bean;

import java.io.Serializable;
import java.util.Comparator;

public abstract class Offerte implements Cloneable,Serializable,Comparable<Offerte>{
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore della superClasse Offerte
	 * @param id
	 * @param prezzo
	 */
	public Offerte(int id, double prezzo) {	
		this.id = id;
		this.prezzo = prezzo;
		prodVenduti = 0;
	}
	
	/**
	 * Aggiorno prodotti venduti con il numero dato in input
	 * @param num
	 */
	public void venduti(int num) {
		prodVenduti += num;
	}
	
	/**
	 * @return id;
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return prodottiVenduti
	 */
	public int getProdVenduti() {
		return prodVenduti;
	}
	
	/**
	 * @return prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}
	
	/**
	 * Imposta prezzo
	 * @param price
	 */
	public void setPrezzo(double price){
		prezzo = price;
	}

	/**
	 * Sovrascrive il metodo toString() di Object
	 */
	public String toString(){
		return  "[ID: " + id + "]" + " [Prezzo: " + prezzo + "]" +" [Acquistati: " + prodVenduti + "]";
	}
	
	/**
	 * Sovrascrive il metodo equals() di Object
	 */
	public boolean equals(Object otherObject) {
		Offerte o;
		if (otherObject.getClass() != getClass()) {
			return false;
		}
		o = (Offerte) otherObject;
		return id == o.getId() && prezzo == o.getPrezzo() && prodVenduti == o.getProdVenduti();
	}
	
	/**
	 * Sovrascrive il metodo clone() di Object
	 */
	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	/**
	 * Metodo astratto per la verifica di un prodotto se e' acquistabile
	 * @return
	 */
	public abstract boolean eAcquistabile();
	
	/**
	 * Classe interna per il riordinamento delle offerte.
	 * Riceve un catalogo con una serie di offerte. 
	 * Effettua un confronto a due a due e ritorna:
	 * numero negativo se l'offerta n.1 è minore dell'offerta n.2
	 * 0 se l'offerta n.1 è uguale all'offerta n.2
	 * 1 se l'offerta n.1 è maggiore dell'offerta n.2
	 * Infine, basterà chiamare il metodo Collection.sort() per effettuare il riordinamento
	 * del catalogo.
	 */
	static class IdComparator implements Comparator<Offerte> {
		public int compare(Offerte arg0, Offerte arg1) {
			return arg0.getId()-arg1.getId();
		}
	}

//Variabili
private int id;
private int prodVenduti;
private double prezzo;
}
