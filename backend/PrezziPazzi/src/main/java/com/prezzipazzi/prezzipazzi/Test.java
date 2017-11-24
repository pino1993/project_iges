package com.prezzipazzi.prezzipazzi;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Test {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		Admin a = new Admin ();
		Scanner in = new Scanner(System.in);
		String scelta;
		//int sceltaUser;
		File f = new File("admin.txt");
		
		a.logIn(f, "admin", "admin");
		a.sort();
		
	
		
		/*
		a.addProdotto(new BeniDiConsumo(a.getId(),100,"Nike Air force 1",30,"FootLocker", 4));
		
		a.addProdotto(new Cene(a.getId(),50,"Napoli","\n"+"0) Antipasto a buffet\n1) Spaghetti ai frutti di mare \n2) Orata con patate\n3) Macedonia con Gelato","Bella Napoli",new GregorianCalendar(2014,0,24),10));
		a.addProdotto(new PrestazioniOpera(a.getId(),80, "Cupertino", "Keynote Apple","Apple",5));
		a.salva();
		System.out.println("Ecco le offerte scadute: ");
		a.offerteNonAcquistabili();
		System.out.println("Ecco le offerte scontate presenti nel catalogo: ");
		a.prodottiScontati();
		
		a.addProdotto(new PrestazioniOpera(a.getId(),80, "Cupertino", "Keynote Apple","Apple",5));
		*/
		/*
		for (;;) {
			System.out.println("In che modo vuoi ordinare le offerte persenti nel catalogo?");
			System.out.println("[Premi 0] ID - [Premi 1] Cronologico");
			sceltaUser = in.nextInt();
			if(sceltaUser == 0 || sceltaUser == 1) {
				a.sceltaOrdinamento(sceltaUser);
				break;
			}
		}
		*/
		//a.prodottiScontati();//applica gli sconti alle cene e alle vacanze che sono in scadenza nell'ultima settimana 

	
		
		
		for(;;) {
			System.out.println("Vuoi rimuovere un'offerta?" + "[SI/NO]");
			scelta = in.next();
			if (scelta.equalsIgnoreCase("si")){
				System.out.println("Quale oggetto vuoi rimuovere su " + a.size() + " offerte disponibili?");
				int oggetto = in.nextInt();
				if (oggetto >= 0 && oggetto <= a.size()){
					a.rmvProdotto(oggetto);
					break;
				}
				else
					System.out.println("Non ho capito");
			}
			else if (scelta.equalsIgnoreCase("no")) {
				System.out.println("Ricevuto! Ora procedo con l'ordinamento");
				break;
			}
			else {
				System.out.println("Scelta non corretta");
			}
		}
		a.stampa();

		User u=new User("userLogin.txt");
		String s2,s3;

		System.out.println("Sei gia registrato al sistema? [SI/NO]");
		String s=in.next();
		if(s.equalsIgnoreCase("si"))
		{
			System.out.println("Inserisci il tuo id nel sistema");
			s2=in.next();
			System.out.println("Inserisci la tua password");
			s3=in.next();
			u.login(s,s2,s3);
		}
		else if(s.equalsIgnoreCase("no"))
		{
			System.out.println("Inserisci il tuo id per registrarti nel sistema");
			s2=in.next();
			System.out.println("Inserisci la tua password");
			s3=in.next();
			u.login(s,s2,s3);
		}
		else {
			System.out.println("Risposta non consentita, programma terminato.");
			return;
		}

		//Acquisto credito
		System.out.println("Inserire credito:");
		int amount = in.nextInt();
		u.credito(amount);
		System.out.println("Vuoi visualizzare offerta attive nel catalogo ?" + "[SI/NO]");
		scelta = in.next();
		if (scelta.equalsIgnoreCase("si")){
			System.out.println("Le offerte ancora attive nel catalogo sono :");
			u.acquistabili("Napoli");
		}
		System.out.println();

		System.out.println("Inserisci il un giudizio da 1 a 5 per visualizzare solo i fornitori con giudizio scelto da te");
		int giudizio=in.nextInt();
		u.fornitore(giudizio, a.getCat());

		System.out.println("Inserisci una data iniziale e una finale per controllare solo i beni compresi in questo periodo");
		int giorno=in.nextInt();
		int mese=in.nextInt();
		int anno=in.nextInt();
		System.out.println("Inserisci la scadenza");
		int gg=in.nextInt();
		int mm=in.nextInt();
		int aaaa=in.nextInt();
		GregorianCalendar inizio = new GregorianCalendar(anno, mese - 1, giorno);
		GregorianCalendar fine = new GregorianCalendar(aaaa, mm - 1, gg);
		u.scadenza(inizio, fine, a.getCat());

		System.out.println("Vuoi effetuare uno acquisto?" + "[SI/NO]");
		scelta = in.next();
		if (scelta.equalsIgnoreCase("si")) {
			System.out.println("Stai per effetuare un acquisto...");
			String acquistare=null;
			int ID;
			do{
				System.out.println("Inserire la quantità dell'acquisto: ");
				int quantita = in.nextInt();
				System.out.println("Inserisci l'id dell'offerta da acquistare: ");
				ID=in.nextInt();
				System.out.println(u.acquista(ID, a.getCat(),quantita));
				System.out.println("Vuoi effettuare ancora acquisti ?"+"[SI/NO]");
				acquistare=in.next();
			} while(acquistare.equalsIgnoreCase("si"));
		}
		System.out.println("Vuoi visuallizare la tua cronologia di acquisti ?" + "[SI/NO]");
		scelta = in.next();
		if (scelta.equalsIgnoreCase("si")){
			System.out.println("Cronologia acquisti: \n");
			u.storicoCliente();
		}

		in.close();	
	}
}