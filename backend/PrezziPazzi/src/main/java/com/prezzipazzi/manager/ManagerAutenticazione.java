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
                    "SELECT Email_Admin,Password FROM admin WHERE Email_Admin = ? AND Password = ?");

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new AuthException("Email e/o password errate!");
            } else {
                return new Admin(email, null, "Admin", "Admin");

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

    public synchronized User loginUser(String email, String password)
            throws SQLException, AuthException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Database.getConnessione();
            pstmt = conn.prepareStatement(
                   "SELECT Email_Utente,Password  FROM utenti WHERE Email_Utente = ? AND Password = ?");

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new AuthException("Email e/o password errate");
            } else {
                pstmt.close();

//                pstmt = conn.prepareStatement("UPDATE studente SET date_last_acc = NOW() WHERE email LIKE BINARY ?");
//
//                pstmt.setString(1, email);
//
//                pstmt.executeUpdate();

                return new User(email, null, "Utente", "Cognome");
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
