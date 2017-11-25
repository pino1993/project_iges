/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.manager;

import com.prezzipazzi.bean.Admin;
import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.User;
import com.prezzipazzi.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andrea
 */
public class ManagerAutenticazione {
    
    
    public synchronized Admin loginAdmin(String email, String password)
			throws SQLException, AuthException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = Database.getConnessione();
			pstmt = conn.prepareStatement(
					"SELECT email, password, nome, cognome, matricola, indirizzo, firebaseToken, attiva FROM docente WHERE email LIKE BINARY ? AND password LIKE BINARY SHA2(?, 224)");

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				throw new AuthException("Email e/o password errate");
			} else {

				rs.getString("attiva");
				if (!rs.wasNull()) {
					throw new AuthException("Confermare prima l'indirizzo email");
				}

				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String matricola = rs.getString("matricola");
				String indirizzo = rs.getString("indirizzo");

				pstmt.close();
//				if (token != null) {
//					pstmt = conn.prepareStatement("UPDATE docente SET firebaseToken = ? WHERE email LIKE BINARY ?");
//					pstmt.setString(1, token);
//					pstmt.setString(2, email);
//					pstmt.executeUpdate();
//					pstmt.close();
//				}

				pstmt = conn.prepareStatement("UPDATE docente SET date_last_acc = NOW() WHERE email LIKE BINARY ?");

				pstmt.setString(1, email);

				pstmt.executeUpdate();

				return new Admin(email, null, nome, cognome);

			}

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
	}

	public synchronized User loginUser(String email, String password)
			throws SQLException, AuthException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = Database.getConnessione();
			pstmt = conn.prepareStatement(
					"SELECT email, password, nome, cognome, matricola, firebaseToken, attiva FROM studente WHERE email LIKE BINARY ? AND password LIKE BINARY SHA2(?, 224)");

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				throw new AuthException("Email e/o password errate");
			} else {

				rs.getString("attiva");
				if (!rs.wasNull()) {
					throw new AuthException("Confermare prima l'indirizzo email");
				}

				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String matricola = rs.getString("matricola");

				pstmt.close();

//				if (token != null) {
//					pstmt = conn.prepareStatement("UPDATE studente SET firebaseToken = ? WHERE email LIKE BINARY ?");
//					pstmt.setString(1, token);
//					pstmt.setString(2, email);
//					pstmt.executeUpdate();
//					pstmt.close();
//				}
				pstmt = conn.prepareStatement("UPDATE studente SET date_last_acc = NOW() WHERE email LIKE BINARY ?");

				pstmt.setString(1, email);

				pstmt.executeUpdate();

				return new User(email, null, nome, cognome);
			}

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}

	}
    
}
