package com.prezzipazzi.bean;

import java.util.ArrayList;


/**
 * @author AndreaValenti
 */
public class User extends Utente{
    
        /**
	 * @param email
	 * @param password
	 * @param nome
	 * @param cognome
	 */
	public User(String email, String password, String nome, String cognome) {
		super(email, password, nome, cognome);
                this.credito = 0;
	}
	
	public User(){
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User " + super.toString() + "]";
	}
	
	/**
	 * Metodo che consente di visualizzare la cronologia acquisti del cliente
         * TODO DA MODIFICARE
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
         *  * TODO DA MODIFICARE
	 */
	public Offerte getItem(int i){
		return array.get(i);
	}
        
        public double sottraiCredito(double valore) throws Exception{
            if(valore > credito){
                //lancio eccezzione
                throw new Exception("Credito inferiore alla somma acquistata");
            }
            else{
                credito = credito - valore;
                return credito;
            }
        }

	/**
	 * 
	 * @return numOfferte
         *  * TODO DA MODIFICARE
	 */
	public int size(){
		return array.size();
	}

	/** 
	 * @return credito
         *  * TODO DA MODIFICARE
	 */
	public double getCredito() {
		return credito;
	}


	//Variabili d'istanza
	private double credito;
	private ArrayList<Offerte> array;
}