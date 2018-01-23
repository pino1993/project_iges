package com.prezzipazzi.manager;

import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.BeniDiConsumo;
import com.prezzipazzi.bean.Catalogo;
import com.prezzipazzi.bean.Cene;
import com.prezzipazzi.bean.Offerte;
import com.prezzipazzi.bean.PrestazioniOpera;
import com.prezzipazzi.bean.Vacanze;
import com.prezzipazzi.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 *
 * @author giuse
 */
public class ManagmentOfferte {
    
     public synchronized Catalogo getAllOffers()
            throws SQLException, AuthException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Database.getConnessione();
            pstmt = conn.prepareStatement(
                    "SELECT * FROM offerte");

            
            ResultSet rs = pstmt.executeQuery();
            String id,img,desc;
            Double prezzo;
            Offerte off;
            Catalogo c = new Catalogo();
            if (!rs.isBeforeFirst()) {
                throw new AuthException("Nessuna Offerta");
            }
           
            while (rs.next()) {
              
               GregorianCalendar cal =  new GregorianCalendar();
               GregorianCalendar cal2 =  new GregorianCalendar();
               switch(rs.getString("Tipo")){
                   case "BeniDiConsumo":
                       c.addOfferta(new BeniDiConsumo(rs.getInt("Id_Offerte"), rs.getDouble("Prezzo"),rs.getString("Immagine"),rs.getString("Descrizione"),rs.getInt("Disponibilità"),rs.getString("Nome_Fornitore"),rs.getInt("Giudizio")));
                   break;
                   
                   case "Cene":
                       cal.setTime(rs.getDate("Scadenza"));
                       c.addOfferta(new Cene(rs.getInt("Id_Offerte"),rs.getDouble("Prezzo"),rs.getString("Immagine"),rs.getString("Descrizione"),rs.getString("Ristorante"),rs.getString("Località"),cal,rs.getInt("Disponibilità")));
                   break;
                   
                   case "PrestazioniOpera":
                       
                       c.addOfferta(new PrestazioniOpera(rs.getInt("Id_Offerte"),rs.getDouble("Prezzo"),rs.getString("Immagine"),rs.getString("Località"),rs.getString("Descrizione"),rs.getString("Nome_Fornitore"),rs.getInt("Giudizio")));
                   break;
                   
                   case "Vacanze":
                       cal.setTime(rs.getDate("Scadenza"));
                       cal2.setTime(rs.getDate("Data_Partenza"));
                       c.addOfferta(new Vacanze(rs.getInt("Id_Offerte"),rs.getDouble("Prezzo"),rs.getString("Immagine"),rs.getString("Descrizione"),rs.getString("Località"),cal,cal2));
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
