/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.manager;

import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.BeniDiConsumo;
import com.prezzipazzi.bean.Catalogo;
import com.prezzipazzi.bean.Cene;
import com.prezzipazzi.bean.Offerte;
import com.prezzipazzi.bean.PrestazioniOpera;
import com.prezzipazzi.bean.Vacanze;
import com.prezzipazzi.database.Database;
import com.prezzipazzi.storico.StoricoServlet;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giuse
 */
public class ManagmentStorico {
    
     public synchronized Catalogo getStoricoOfferte(String idOfferte,String emailUtente)
            throws SQLException, AuthException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Database.getConnessione();
            pstmt = conn.prepareStatement(
                    "SELECT Id_Offerte FROM prodotti_acquistati WHERE Id_Offerte= ? AND Email_Utente = ?");

            pstmt.setString(1,idOfferte);
            pstmt.setString(2,emailUtente);
            ResultSet rs = pstmt.executeQuery();
            
 
            String offers="";
           
            if (rs == null) {
                throw new AuthException("Nessuna Offerta");
            }
            while (rs.next()) {
              
                 offers = rs.getString("Id_Offerte");
                 
              
               
            }
        

        
            conn = Database.getConnessione();
            pstmt = conn.prepareStatement(
                    "SELECT * FROM offerte WHERE Id_Offerte= ?");

            pstmt.setString(1,offers);
            ResultSet rst = pstmt.executeQuery();
            
            String id,img,desc;
            Double prezzo;
            Offerte off;
            Catalogo cat;
            Catalogo c = new Catalogo();
           
           
            if (rst == null) {
                throw new AuthException("Nessuna Offerta");
            }
               while (rst.next()) {
              
               GregorianCalendar cal =  new GregorianCalendar();
               GregorianCalendar cal2 =  new GregorianCalendar();
               switch(rst.getString("Tipo")){
                   case "BeniDiConsumo":
                       c.addOfferta(new BeniDiConsumo(rst.getInt("Id_Offerte"), rst.getDouble("Prezzo"),rst.getString("Immagine"),rst.getString("Descrizione"),rst.getInt("Disponibilità"),rst.getString("Nome_Fornitore"),rst.getInt("Giudizio")));
                   break;
                   
                   case "Cene":
                       cal.setTime(rst.getDate("Scadenza"));
                       c.addOfferta(new Cene(rst.getInt("Id_Offerte"),rst.getDouble("Prezzo"),rst.getString("Immagine"),rst.getString("Descrizione"),rst.getString("Ristorante"),rst.getString("Località"),cal,rst.getInt("Disponibilità")));
                   break;
                   
                   case "PrestazioniOpera":
                       
                       c.addOfferta(new PrestazioniOpera(rst.getInt("Id_Offerte"),rst.getDouble("Prezzo"),rst.getString("Immagine"),rst.getString("Località"),rst.getString("Descrizione"),rst.getString("Nome_Fornitore"),rst.getInt("Giudizio")));
                   break;
                   
                   case "Vacanze":
                       cal.setTime(rst.getDate("Scadenza"));
                       cal2.setTime(rst.getDate("Data_Partenza"));
                       c.addOfferta(new Vacanze(rst.getInt("Id_Offerte"),rst.getDouble("Prezzo"),rst.getString("Immagine"),rst.getString("Descrizione"),rst.getString("Località"),cal,cal2));
                   break;
                 
               }
               
            }
            rs.close();
           
            return c; 


        } finally {
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

