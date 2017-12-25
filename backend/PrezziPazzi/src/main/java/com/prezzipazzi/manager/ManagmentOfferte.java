package com.prezzipazzi.manager;

import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.BeniDiConsumo;
import com.prezzipazzi.bean.Catalogo;
import com.prezzipazzi.bean.Offerte;
import com.prezzipazzi.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            
            if (rs == null) {
                throw new AuthException("Nessuna Offerta");
            }
            
            while (rs.next()) {
//                id = rs.getString("Id_Offerte");
//		prezzo = rs.getDouble("Prezzo");
//                desc = rs.getString("Descrizione");
//                img = rs.getString("Immagine");
//                System.out.println("ECCO I RISUILTATI DELLA QUERY : "+id+" e "+prezzo+" descrizione: "+desc+" img: "+img);

               switch(rs.getString("Tipo")){
                   case "BeniDiConsumo":
                       c.addOfferta(new BeniDiConsumo(rs.getInt("Id_Offerte"), rs.getDouble("Prezzo"),rs.getString("Immagine"),rs.getString("Descrizione"),rs.getInt("Disponibilità"),rs.getString("Nome_Fornitore"),rs.getInt("Giudizio")));
                   break;
                   
                   case "Cene":
                       //c.addOfferta(new Cene());
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
