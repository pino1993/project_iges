package com.prezzipazzi.bean;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class Catalogo {
	/**
	 * Costruttore che crea un nuovo ArrayList e se la condizione si verifica carica il catalogo
	 * @param load
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Catalogo(boolean load) throws FileNotFoundException, IOException, ClassNotFoundException {
		id = 1;
		prodotti = new ArrayList<Offerte>();
		
		if(load == true) {
			prodotti = carica();
		}
	}

	/**
	 * Metodo che restituisce l'id aggiornato.
	 * @param prod
	 * @return id
	 */
	public int dammiId(int prod) {
		return id + prod;
	}
	
	/**
	 * Metodo che restituisce l'id dell'offerta data in ingresso.
	 * @param o
	 * @return id
	 */
	public int getId(Offerte o){
		return o.getId();
	}

	/**
	 * Metodo che consente di aggiungere l'offerta data in input al catalogo.
	 * @param prodotto
	 */
	public void addOfferta(Offerte prodotto) {
		prodotti.add(prodotto);
	}

	/**
	 * Metodo che consente di rimuovere l'offerta data in input dal catalogo.
	 * @param id
	 */
	public void rmvOfferte(int id) {
		prodotti.remove(id);
	}

	/**
	 * Metodo che restituisce il numero di elementi presenti nel catalogo
	 * @return sizeCatalogo
	 */
	public int size() {
		return prodotti.size();
	}

	/**
	 * Metodo che restituisce i prodotti presenti nel catalogo
	 * @return prodottiCatalogo
	 */
	public ArrayList<Offerte> getArray() {
		return prodotti;
	}
	
	/**
	 * Metodo che restituisce l'indice del prodotto dato in input se presente, altrimenti non restituisce nulla 
	 * se l'indice del prodotto non si trova nel catalogo
	 * @param i
	 * @return offerta
	 */
	public Offerte getItem(int i) {
		if(i < prodotti.size())
			return prodotti.get(i);
		else
			return null;
	}

	/**
	 * Metodo che scambia due offerte per effettuare un riordinamento
	 * @param i
	 * @param j
	 */
	public void swap(int i, int j) {
		Offerte temp = prodotti.get(i);

		prodotti.set(i, prodotti.get(j));
		prodotti.set(j, temp);
	}
	
	/**
	 * Metodo che viene utilizzato per la stampa a video di tutte le offerte presenti
	 * nel catalogo.
	 */
	public void stampa() {
		Offerte o;
		for (int i = 0; i < prodotti.size(); i++) {
			o = prodotti.get(i);
			System.out.println(o);
		}
	}

	/**
	 * Metodo che controlla quale offerte non sono acquistabili
	 */
	public void offerteNonAcquistabili() {
		boolean flag = true;
		
		for(int i = 0; i < prodotti.size(); i++) {
			Offerte u = prodotti.get(i);
			if(!u.eAcquistabile()) {
				flag = false;
				System.out.println("L'offerta " + (u.getId()+1) + " non e' acquistabile.");
			}
		}
		if (flag == true) {
			System.out.println("Non ci sono offerte scadute\n");
		}
	}

	/**
	 * Metodo che controlla quale offerte sono acquistabili
	 * @param luogo
	 */
	public void offerteAcquistabili(String luogo) {
		boolean flag = true;
		
		for(int i=0; i < prodotti.size(); i++) {
			Offerte u = prodotti.get(i);
			if(u.eAcquistabile()) {
				flag = false;
				System.out.println("L'offerta : " + (u.getId()+1) + " e' acquistabile"); 
			}
		}
		if (flag == true) {
			System.out.println("Non ci sono offerte al momento\n");
		}
	}

	/**
	 * Metodo per il riordinamento del catalogo
	 */
	public void sort(){
		Collections.sort(prodotti,new Offerte.IdComparator());
	}

	/**
	 * Metodo che carica nel catalogo tutti gli elementi presenti nel file catalogo.dat 
	 * @return catalogo
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Offerte> carica () throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("catalogo.dat"));
		Offerte u;
		
		// Array da ritornare con gli oggetti del file
		ArrayList<Offerte> catalogo = new ArrayList<Offerte>();
		
		//Continua fino a quando non cattura EOFException ed interrompe il ciclo
		while(true) { 
			try {
				u = (Offerte) in.readObject();
			}catch(EOFException e){
				break;
			}
			catalogo.add(u);
		}
		in.close();
		return catalogo;
	}
	
	//Variabili
	private ArrayList<Offerte> prodotti;
	private int id;
}