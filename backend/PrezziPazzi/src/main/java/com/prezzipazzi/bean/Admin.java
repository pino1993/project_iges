package com.prezzipazzi.bean;


/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class Admin extends Utente{

	/**
	 * @param email
	 * @param password
	 * @param nome
	 * @param cognome
	 */
	public Admin(String email, String password, String nome, String cognome) {
		super(email, password, nome, cognome);
	}
	
	public Admin(){
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Admin " + super.toString() + "]";
	}
}