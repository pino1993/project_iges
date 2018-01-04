/**
 *
 * @author andrea
 */
package com.prezzipazzi.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class Database {

    @Resource(lookup = "jdbc/DbPrezziPazzi")
    private static DataSource ds;

    static {
    }

    public static Connection getConnessione() throws SQLException {
        try {
            return ds.getConnection();
        } catch (NullPointerException e) {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DbPrezziPazzi?autoReconnect=true&useSSL=false", "root", "root");
            //Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DbPrezziPazzi?autoReconnect=true&useSSL=false", "root", "");
            return myConn;
        }
    }

}
