/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.autenticazione;

/**
 *
 * @author andrea
 */
public class AuthException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AuthException(String message) {
        super(message);
    }

}
