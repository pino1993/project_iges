/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.test;

import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.Utente;
import com.prezzipazzi.manager.ManagerAutenticazione;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;

/**
 *
 * @author ruggerotammaro
 */
public class NewEmptyJUnitTest extends DatabaseTestCase  {
    private FlatXmlDataSet loadedDataSet;
    private Connection jdbcConnection;
    
    @Resource(lookup = "jdbc/DbPrezziPazzi")
    private static DataSource ds;
    
    @Override
    protected IDatabaseConnection getConnection() throws Exception {
       try {
            return (IDatabaseConnection) ds.getConnection();
        } catch (NullPointerException e) {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DbPrezziPazzi?autoReconnect=true&useSSL=false", "root", "root");
            //Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DbPrezziPazzi?autoReconnect=true&useSSL=false", "root", "");
            return (IDatabaseConnection) myConn;
        }
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader()
				.getResourceAsStream("dbprezzipazzi.xml"));
		return loadedDataSet;
    }
    public void testLogin() throws SQLException, AuthException {
        ManagerAutenticazione mAutenticazione = new ManagerAutenticazione();
        Utente ute = mAutenticazione.loginUser("ruggero@email.it", "1234");;
		assertEquals("ruggero", ute.getNome());
	}

	/** *Test case for calculator *negative scenario---InValid Employee */
	@Test
	public void testCalculatorNeg() throws SQLException, AuthException {
		ManagerAutenticazione mAutenticazione = new ManagerAutenticazione();
        Utente ute = mAutenticazione.loginUser("ruggero@email.it", "1234");;
		assertEquals("pino", ute.getNome());
	}
}


