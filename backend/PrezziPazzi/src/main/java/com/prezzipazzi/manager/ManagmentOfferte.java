package com.prezzipazzi.manager;

import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.Offerte;
import com.prezzipazzi.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author giuse
 */
public class ManagmentOfferte {
    
     public synchronized Offerte getAllOffers()
            throws SQLException, AuthException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Database.getConnessione();
            pstmt = conn.prepareStatement(
                    "SELECT * FROM offerte");

            
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new AuthException("Nessuna Offerta");
            } else {
                String nome = rs.getString("Descrizione");
		String prezzo = rs.getString("Prezzo");
                rs.close();
                   System.out.println("ECCO I RISUILTATI DELLA QUERY : "+nome+" e "+prezzo);
                  return null; 
            }

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
