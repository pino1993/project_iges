package com.prezzipazzi.prezzipazzi;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class Admin {

	/**
	 * Costruttore che verifica l'esistenza o meno del catalogo e se esso contiene elementi
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Admin() throws FileNotFoundException, ClassNotFoundException, IOException {
		carica = false;

		//controlla se esiste o no il catalogo
		if(new File("catalogo.dat").exists())
			c = new Catalogo(true);
		else
			c = new Catalogo(false);
		counter = 0;

		//se il catalogo contiene elementi
		if(c.size() > 0)
			counter = c.size();	
	}

	/**
	 * Riordina il catalogo per id,chiamando il metodo Collection.sort() e lo aggiorna 
	 * chiamando il metodo salva().
	 * 
	 * @throws EOFException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public void sort() throws EOFException, FileNotFoundException, ClassNotFoundException, IOException, Exception{
		c.sort();
		salva();
	}

	/**
	 * Metodo che ritorna un oggetto di tipo Offerte che riceve in ingresso un intero i, rappresentante l'indice da ricercare nel catalogo, e 
	 * restituiamo l'articolo con l'indice desiderato.
	 * @param i 
	 * @return item
	 */
	public Offerte getItem(int i){
		return c.getItem(i);
	}

	/**
	 * Metodo che ritorna un intero rappresentante l'id aggiornato.
	 * @return id
	 */
	public int getId() {
		Offerte temp;
		for (int i = 0; i < c.size() ; i++) {
			temp = c.getItem(i);
			counter = temp.getId();
		}
		return c.dammiId(counter);
	}

	/**
	 * Metodo che riceve in ingresso un oggetto di tipo Offerte e lo aggiunge al catologo se 
	 * la condizione lo permette.
	 * @param prodotto
	 */
	public void addProdotto(Offerte prodotto) {
		if(carica == true)
			c.addOfferta(prodotto);
	}

	/**
	 * Metodo che restituisce un intero, rappresentante il numero di elementi presenti nel catalogo.
	 * @return sizeCatalogo
	 */
	public int size() {
		return c.size();
	}

	/**
	 * Metodo che consente di stampare il catalogo.
	 */
	public void stampa() {
		c.stampa();
	}

	/**
	 * Metodo che restituisce un oggetto di tipo Catalogo, rappresentante il catalogo offerte.
	 * @return catalogo
	 */
	public Catalogo getCat() {
		return c;
	}

	/**
	 * Offerte non acquistabili
	 */
	public void offerteNonAcquistabili() {
		c.offerteNonAcquistabili();
	}
	/**
	 * Offerte non acquistabili
	 */
	public void offerteAcquistabili(String luogo) {
		c.offerteAcquistabili(luogo);
	}
	/**
	 * Metodo che attiva la politica sconti chiamando i metodi scontiCene() e scontiViaggi() ed infine aggiorna il catalogo.
	 * @throws EOFException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public void prodottiScontati() throws EOFException, FileNotFoundException, ClassNotFoundException, IOException, Exception{
		scontiCene();
		scontiViaggi();
		salva();
	}

	/**
	 * Metodo che controlla quale viaggio,presente nel catalogo, ha diritto allo sconto.
	 */
	private void scontiViaggi(){
		int i = 0;

		while(i<c.size()){
			if(c.getItem(i) instanceof Vacanze && c.getItem(i).eAcquistabile()){
				Vacanze v=(Vacanze)c.getItem(i);
				double price = v.getPrezzo();
				int giornoS = v.getScadenza().get(Calendar.WEEK_OF_MONTH);
				int meseS = v.getScadenza().get(Calendar.MONTH) + 1;
				int annoS = v.getScadenza().get(Calendar.YEAR);
				GregorianCalendar oggi=new GregorianCalendar();
				int mese = oggi.get(Calendar.MONTH) + 1;
				int anno = oggi.get(Calendar.YEAR);
				int giornoSett = oggi.get(Calendar.WEEK_OF_MONTH);

				if(annoS == anno && meseS == mese && giornoS==giornoSett){ 
					price -= (price * 0.2);
					v.setPrezzo(price);
				}
			}
			i++;	
		}
	}

	/**
	 * Metodo che controlla quale cena,presente nel catalogo, ha diritto allo sconto.
	 */
	private void scontiCene(){
		int i = 0;

		while(i<c.size()){
			if(c.getItem(i) instanceof Cene && c.getItem(i).eAcquistabile()){
				Cene ce=(Cene)c.getItem(i);
				int flag=0;
				double price = ce.getPrezzo();
				int giornoS = ce.getScadenza().get(Calendar.WEEK_OF_MONTH);
				int meseS = ce.getScadenza().get(Calendar.MONTH) + 1;
				int annoS = ce.getScadenza().get(Calendar.YEAR);

				GregorianCalendar dataOdierna = new GregorianCalendar();
				int mese = dataOdierna.get(Calendar.MONTH) + 1;
				int anno = dataOdierna.get(Calendar.YEAR);
				int giornoSett = dataOdierna.get(Calendar.WEEK_OF_MONTH);

				if(annoS == anno && meseS == mese && giornoS==giornoSett)
					flag=1;

				if(flag ==1) {
					if(ce.getProdVenduti() < (ce.getDaVendere()/2)) {
						price -= (price * 0.15);
						ce.setPrezzo(price);
					}
				}
			}
			i++;
		}
	}

	/**
	 * Metodo che consente di rimuovere le offerte presenti nel catalogo.
	 * @param id
	 * @throws EOFException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public void rmvProdotto(int id) throws EOFException, FileNotFoundException, ClassNotFoundException, IOException, Exception {
		int i = 0;
		//Copia del catalogo
		ArrayList<Offerte> o = c.getArray();	

		//Se il login non si verifica, esce
		if(carica == false)
			return;	

		while(i < c.size()) {

			//cerca l'oggetto con Id uguale a quello dato in input
			if(c.getId(o.get(i)) == id) {	
				c.rmvOfferte(i);				
				salva();						
				return;							
			}
			i++;
		}
	}

	/**
	 * Metodo che consente di ordinare il catalogo per data di scadenza,chiamando il metodo ordinamentoCronologico.
	 * @throws EOFException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public void OrdinamentoCrono() throws EOFException, FileNotFoundException, ClassNotFoundException, IOException, Exception {
		ordinamentoCronologico(c);
	}

	/**
	 * Metodo che riordina il catalogo e lo aggiorna chiamando il metodo salva
	 * @param temp
	 * @throws EOFException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	private void ordinamentoCronologico(Catalogo temp) throws EOFException, FileNotFoundException, ClassNotFoundException, IOException, Exception {

		int i = temp.size() - 1;
		int j = 0;
		Cene primaCena = null;
		Cene secondaCena = null;
		Vacanze primaVacanza = null;
		Vacanze secondaVacanza = null;

		while(i > 0) {
			j = 0;
			while(j < i) {
				//BeniDiConsumo
				if(temp.getItem(j) instanceof BeniDiConsumo && !(temp.getItem(j+1) instanceof BeniDiConsumo))
					temp.swap(j, j+1);
				//Prestazioni d'Opera
				if(temp.getItem(j) instanceof PrestazioniOpera && !(temp.getItem(j+1) instanceof PrestazioniOpera)) 
					temp.swap(j, j+1);
				//Due Cene
				if (temp.getItem(j) instanceof Cene && temp.getItem(j+1) instanceof Cene) {
					primaCena = (Cene) temp.getItem(j);
					secondaCena = (Cene) temp.getItem(j+1);
					if(primaCena.getScadenza().after(secondaCena.getScadenza()))
						temp.swap(j, j+1);
				}
				//Due Vacanze
				if(temp.getItem(j) instanceof Vacanze && temp.getItem(j+1) instanceof Vacanze) {
					primaVacanza = (Vacanze) temp.getItem(j);
					secondaVacanza = (Vacanze) temp.getItem(j+1);
					if(primaVacanza.getScadenza().after(secondaVacanza.getScadenza()))
						temp.swap(j, j+1);
				}
				//Una Cena e una Vacanza
				else if (temp.getItem(j) instanceof Cene && temp.getItem(j+1) instanceof Vacanze) {
					primaCena = (Cene) temp.getItem(j);
					secondaVacanza = (Vacanze) temp.getItem(j+1);
					if(primaCena.getScadenza().after(secondaVacanza.getScadenza()))
						temp.swap(j, j+1);
				} 
				//Una Vacanza e una Cena
				else if (temp.getItem(j) instanceof Vacanze && temp.getItem(j+1) instanceof Cene) {
					primaVacanza = (Vacanze) temp.getItem(j);
					secondaCena = (Cene) temp.getItem(j+1);
					if(primaVacanza.getScadenza().after(secondaCena.getScadenza()))
						temp.swap(j, j+1);	
				}
				j++;
			}
			i--;
		}
		salva();
	}

	/**
	 * Metodo che consente di effettuare il Login al sistema, controllando nel file se i dati passati in input si trovano nel 
	 * file delle registrazioni 
	 * @param file
	 * @param nome
	 * @param password
	 * @return
	 * @throws FileNotFoundException
	 */
	public int logIn(File file, String nome, String password) throws FileNotFoundException {	
		Scanner in = new Scanner(file);

		while(in.hasNext()) {
			if (in.next().equals(nome)) {
				if (in.next().equals(password)) {
					this.carica = true;
					in.close();
					return 1;
				}
			}
			else 
				in.next();
		}
		in.close();
		return -1;
	}

	/**
	 *  Controlla attraverso il valore di ritorno di carica, se il catalogo è stato riempito 
	 *  con tutte le offerte presenti nel file.
	 *  @return
	 */
	public boolean caricamento() {
		return carica;
	}
	
	/**
	 * Metodo che consente di aggiornare il catalogo ogni volta che si effettua una modifica. 
	 * @throws Exception
	 * @throws IOException
	 * @throws EOFException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void salva() throws Exception, IOException, EOFException,FileNotFoundException, IOException, ClassNotFoundException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("catalogo.dat"));
		int i = 0;
		ArrayList<Offerte> temp = c.getArray();
		
		//aggiorna l'oggetto i del catalogo
		while(i < c.getArray().size()) {
			out.writeObject(temp.get(i)); 
			i++;
		}
		out.close();
	}
	
	//Variabili
	private int counter;
	private Catalogo c;
	private boolean carica;
}