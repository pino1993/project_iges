package progettoPart2;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class Vacanze extends Offerte{
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore che eredita l'id ed il prezzo dalla classe Offerte ed aggiunge altre caratteristiche.
	 * @param id
	 * @param prezzo
	 * @param l
	 * @param p
	 * @param s
	 */
	public Vacanze(int id, double prezzo,String l,GregorianCalendar p,GregorianCalendar s) {
		super(id, prezzo);
		localita = l;
		partenza = p;
		scadenza = s;
	}
	
	/**
	 * Controlla se l'offerta puo essere acquistata, in base alla sua data di scadenza.
	 */
	public boolean eAcquistabile() {
		if(scadenza.before(new GregorianCalendar()))
			return false;
		else
			return true;
	}
	
	/**
	 * @return localita
	 */
	public String getLocalita() {
		return localita;
	}
	/**
	 * @return dataPartenza
	 */
	public GregorianCalendar getPartenza() {
		return partenza;
	}
	/**
	 * @return dataScadenza
	 */
	public GregorianCalendar getScadenza() {
		return scadenza;
	}
	public int compareTo(Offerte arg0) {
		return 0;
	}
	
	/**
	 * Sovrascrive il metodo toString() di Offerte
	 */
	public String toString() {
		int giornoS = scadenza.get(Calendar.DAY_OF_MONTH);
		int meseS = scadenza.get(Calendar.MONTH) + 1;
		int annoS = scadenza.get(Calendar.YEAR);
		int giorno = partenza.get(Calendar.DAY_OF_MONTH);
		int mese = partenza.get(Calendar.MONTH);
		int anno = partenza.get(Calendar.YEAR);
		
		return super.toString() + " [Luogo: " + localita + "]" + " [Partenza: " + giorno + "/" + mese + "/" + anno + "]" + " [Scadenza: " + giornoS + "/" + meseS + "/"+ annoS +"]";
	}
	
	/**
	 * Sovrascrive il metodo equals() di Offerte
	 */
	public boolean equals(Object otherObject) {		
		Vacanze v;	
		if (!(super.equals(otherObject))) {
			return false;
		}
		v = (Vacanze) otherObject;
		return partenza == v.getPartenza() && scadenza == v.getScadenza() && localita.equalsIgnoreCase(localita);
	}

	/**
	 * Sovrascrive il metodo clone() di Offerte
	 */
	public Object clone() {	
		return super.clone();
	}
	
	private String localita;
	private GregorianCalendar partenza, scadenza;

}
