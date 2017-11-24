package progettoPart2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

/**
 * @author GiovanniGenovese-AndreaValenti
 */
public class User {
	/**
	 * Costruttore che riceve in ingresso un File contente gli utenti registrati al sistema con credito iniziale a 0. 
	 * @param FileName
	 */
	public User (String FileName) {
		this.FileName = FileName;
		credito = 0;

		//Cronologia
		array = new ArrayList<Offerte>();
		if(new File("catalogo.dat").exists()){
			try {
				c = new Catalogo(true);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			try {
				catalogo = c.carica();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				c = new Catalogo(false);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			catalogo = new ArrayList<Offerte>();
		}
	}

	/**
	 * Metodo che consente di accedere al sistema oppure, se e' la prima volta, di registrarsi
	 * @param s
	 * @param nome
	 * @param pass
	 * @return login
	 * @throws Exception
	 */
	public int login (String s,String nome,String pass) throws Exception {

		int risultato,r;
		if (s.equalsIgnoreCase("no")){
			r = registra(nome,pass);
			return r;
		}
		else if(s.equalsIgnoreCase("si")){
			risultato = accedi(nome,pass);
			if(risultato == 1)
				return 1;
			else
				return 0;
		}
		else
			System.out.println("Scelta non consentita");
		return 0;
	}

	/**
	 * Metodo interno per accedere al sistema
	 * @param id=Nome utente
	 * @param pass=password
	 */
	private int accedi(String id, String pass) throws FileNotFoundException {
		FileReader reader = new FileReader(FileName);

		int accesso = 0;
		BufferedReader b = new BufferedReader(reader);
		String name = null,passw = null,credit = null;


		while(true) {
			try {
				name=b.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(name == null)
				break;
			try {
				passw=b.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(passw == null)
				break;
			try {
				credit=b.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(credit== null)
				break;

			if(id.equals(name) && pass.equals(passw)){
				accesso = 1;
				credito = Double.parseDouble(credit);
				nomeUt = id;
				passUt = pass;
			}	
		}
		try {
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accesso;
	}

	/** 
	 * Metodo che consente di registrare un utente al sistema
	 * @param Nome utente
	 * @param password utente
	 */
	private int registra(String id,String pass) throws Exception {
		File f = new File(FileName);

		FileOutputStream fos = new FileOutputStream(f, true);
		PrintStream file = new PrintStream(fos);
		int result = accedi(id,pass);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "User non disponibile", "Errore", JOptionPane.INFORMATION_MESSAGE);
			file.close();
			return 0;
		}
		else {
			file.println(id);
			file.println(pass);
			file.println(0);
			file.close();
			JOptionPane.showMessageDialog(null, "User: " + id + "\nPassword: " + pass , "Registrazione Effettuata", JOptionPane.INFORMATION_MESSAGE);
			return 1;
		}
	}

	/**
	 * Metodo che aggiunge al credito attuale l'importo dato in input e aggiorna il file con il metodo aggiorna().
	 * @param soldi
	 */
	public void credito(double soldi) {
		File fa = new File(FileName);
		FileReader reader = null;
		try {
			reader = new FileReader(fa);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader b;
		b = new BufferedReader(reader);

		File f = new File("prova.txt");
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintStream file = new PrintStream(fos);
		String nome = null;
		String pa = null;
		String credit = null;

		if (soldi >= 0) {
			while(true) {
				try {
					nome = b.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(nome == null)
					break;
				try {
					pa = b.readLine();
				} catch (IOException e) {

					e.printStackTrace();
				}
				if(pa == null)
					break;
				try {
					credit = b.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(credit == null)
					break;
				if(nomeUt.equals(nome)&& passUt.equals(pa)){
					credito=Double.parseDouble(credit);
					credito+=(soldi/2);
					file.println(nome);
					file.println(pa);
					file.println(credito);
				}
				else {
					file.println(nome);
					file.println(pa);
					file.println(Double.parseDouble(credit));
				}
			}
		}	
		aggiorna();
	}

	/**
	 * Metodo che sottrae dal credito attuale l'importa dato in input ed aggiorna il file chiamando il metodo aggiorna().
	 * @param soldi
	 */
	public void sottrai(double soldi){
		File fa = new File(FileName);
		FileReader reader = null;
		try {
			reader = new FileReader(fa);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader b;
		b = new BufferedReader(reader);

		File f = new File("prova.txt");

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintStream file = new PrintStream(fos);
		String nome = null;
		String pa = null;
		String credit = null;


		while(true) {
			try {
				nome = b.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(nome == null)
				break;
			try {
				pa = b.readLine();
			} catch (IOException e) {

				e.printStackTrace();
			}
			if(pa == null)
				break;
			try {
				credit = b.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(credit == null)
				break;
			if(nomeUt.equals(nome)&& passUt.equals(pa)){
				credito = Double.parseDouble(credit);
				credito -= (soldi);
				file.println(nome);
				file.println(pa);
				file.println(credito);
			}
			else{
				file.println(nome);
				file.println(pa);
				file.println(Double.parseDouble(credit));
			}
		}
		aggiorna();
	}

	/**
	 * Metodo che aggiorna il credito
	 */
	public void aggiorna() {

		File fa = new File("prova.txt");
		FileReader reader = null;

		try {
			reader = new FileReader(fa);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader b = new BufferedReader(reader);
		File f = new File(FileName);

		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		PrintStream file = new PrintStream(fos);
		String nome = null;
		String pa = null;
		String credit = null;

		while(true) {
			try {
				nome = b.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(nome == null)
				break;
			try {
				pa = b.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(pa == null)
				break;
			try {
				credit = b.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(credit == null)
				break;

			file.println(nome);
			file.println(pa);
			file.println(Double.parseDouble(credit));
		}
	}

	/**
	 * Metodo che visualizza le offerte attive nel catalogo
	 */
	public void acquistabili(String luogo) {
		Cene c=null;
		Offerte o=null;

		for(int i=0;i<catalogo.size();i++){
			o=catalogo.get(i);
			if(o instanceof Cene){
				c=(Cene)o;
				if(c.getLuogo().equals(luogo))
					System.out.println(c);
			}
			else
				System.out.println(o);
		}
	}

	public void aggiungi (Offerte u) {
		array.add(u);
	}

	/**
	 * Metodo che permette l'acquisto di un prodotto
	 * @param id
	 * @param c
	 * @param quantita
	 * @return
	 */
	public String acquista(int id,Catalogo c,int quantita) {
		int i = 0;
		double prezzo;
		Offerte off = null;
		boolean flag = true;

		ArrayList<Offerte> u = c.getArray();

		while(i < c.size()) {
			off = u.get(i);
			prezzo = off.getPrezzo();
			i++;
			if ((off.getId() == id)) {
				if (off.eAcquistabile() == false) {
					try {
						throw new Exception("Offerta scaduta");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						break;
					}
				}
				if (off instanceof Cene) {
					Cene ce = (Cene) off;
					if (ce.getDaVendere() < quantita) {
						try {
							throw new Exception("Offerte residue inferiori alla quantità inserita dall'utente");
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
							break;
						}	
					}
				}
				if (off instanceof BeniDiConsumo) {
					BeniDiConsumo b = (BeniDiConsumo) off;
					if (b.getDaVendere() < quantita) {
						try {
							throw new Exception("Offerte residue inferiori alla quantità inserita dall'utente");
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
							break;
						}
					}
				}
					flag = false;
				if ((prezzo * quantita) <= credito) {
					if (quantita > 1 || array.size() > 1) {
						prezzo -= off.getPrezzo() * 0.5;
						JOptionPane.showMessageDialog(null, "Sconto 5%", "Sconto", JOptionPane.INFORMATION_MESSAGE);
					}
					off.venduti(quantita);
					aggiungi(off);
					credito -= prezzo * quantita;
					sottrai(prezzo * quantita);

					if (off instanceof Cene) {
						Cene ce = (Cene) off;
						ce.setDaVendere(quantita);
					}
					else if (off instanceof BeniDiConsumo) {
						BeniDiConsumo b = (BeniDiConsumo) off;
						b.setDaVendere(quantita);
					}
					else ;

					return "Articolo acquistato \nSaldo corrente: "+ credito;
				}
				else
					return "Saldo insufficiente";
			}
		}
		if (flag == true) {
			return "Riprova";
		}
		return "";
	}

	/**
	 * Metodo che consente di visualizzare la cronologia acquisti del cliente
	 */
	public void storicoCliente() {
		for(int i=0;i < array.size();i++) {
			Offerte u = array.get(i);
			System.out.println(u);
		}
	}

	/**
	 * @param i
	 * @return prodotto(i)
	 */
	public Offerte getItem(int i){
		return array.get(i);
	}

	/**
	 * 
	 * @return numOfferte
	 */
	public int size(){
		return array.size();
	}

	/**
	 * Metodo per il fornitore
	 * @param giudizio
	 * @param c
	 */
	public void fornitore(int giudizio,Catalogo c) {

		BeniDiConsumo b;
		PrestazioniOpera p;
		ArrayList<Offerte> u = c.getArray();

		for(int i = 0; i <c.size(); i++){
			if(u.get(i) instanceof BeniDiConsumo){
				b=(BeniDiConsumo) u.get(i);
				if(b.getGiudizio() >= giudizio)
					System.out.println("Il fornitore del bene "+ b +" ha un giudizio positivo");

			}
			if(u.get(i) instanceof PrestazioniOpera){
				p=(PrestazioniOpera) u.get(i);
				if(p.getGiudizio() >= giudizio)
					System.out.println("Il fornitore della prestazione :"+p+" ha un giudizio positivo ");
			}
			else
				;
		}
	}

	/**
	 * Metodo che controlla che l'offerta non sia scaduta
	 * @param inizio
	 * @param fine
	 * @param c
	 */
	public void scadenza(GregorianCalendar inizio,GregorianCalendar fine,Catalogo c){
		Vacanze v;
		Cene ce;
		ArrayList<Offerte> u = c.getArray();

		for(int i = 0 ;i < c.size(); i++){
			if(u.get(i) instanceof Cene) {
				ce=(Cene)u.get(i);
				if(ce.getScadenza().before(fine) && ce.getScadenza().after(inizio))
					System.out.println("La cena"+ ce +" rientra nella data che hai inserito");
			}	
			else if(u.get(i) instanceof Vacanze) {
				v=(Vacanze)u.get(i);
				if(v.getScadenza().before(fine) && v.getScadenza().after(inizio))
					System.out.println("La vacanza"+ v +" rientra nella data che hai inserito");
			}
			else 
				;
		}
	}

	/** 
	 * @return credito
	 */
	public double getCredito() {
		return credito;
	}

	/**
	 * @return nomeUtente
	 */
	public String getNomeUt() {
		return nomeUt;
	}

	//Variabili d'istanza

	private String nomeUt,passUt;
	private Catalogo c;
	private String FileName;
	private double credito;
	private ArrayList<Offerte> array;
	private ArrayList<Offerte> catalogo;
}