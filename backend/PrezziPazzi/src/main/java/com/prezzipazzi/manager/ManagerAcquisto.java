/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.manager;

import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andrea
 */
public class ManagerAcquisto {

    public synchronized boolean purchase(String email, Integer idofferta, double creditoResiduo)
            throws SQLException, AuthException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            System.out.println("Par 1 "+email+" par 2 "+idofferta+" credito "+creditoResiduo);
            conn = Database.getConnessione();
            
            
            pstmt = conn.prepareStatement(
                    "UPDATE utenti SET Credito = ? WHERE Email_Utente = ?");
            pstmt.setDouble(1, creditoResiduo);
            pstmt.setString(2, email);
            
            
            int rs = pstmt.executeUpdate();
            
            System.out.println("Result set"+rs);
            
            String insertTableSQL = "INSERT INTO prodotti_acquistati"
		+ "(Email_Utente, Id_Offerte) VALUES"
		+ "(?,?)";
            
            pstmt = conn.prepareStatement(insertTableSQL);
            pstmt.setString(1, email);
            pstmt.setInt(2, idofferta);
            pstmt.executeUpdate();
            
            
          

            return true;

        } 
        catch(SQLException e){
            System.out.println("com.prezzipazzi.manager.ManagerAcquisto.purchase() Errore"+e);
            return false;
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }
}
