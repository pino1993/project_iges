/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.bean;

/**
 *
 * @author andrea
 */
public abstract class Utente {
	private String nome;
	private String cognome;
	private String email;
	private String password;
	
	public Utente(){
		
	}
	
	/**
	 * @param email
	 * @param password
	 * @param nome
	 * @param cognome
	 */
	public Utente(String email, String password,String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[nome:" + nome + ", cognome:" + cognome +", email:" + email
				+ ", password:" + password ;
	}
}
