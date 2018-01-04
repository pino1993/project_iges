/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.test;

import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.Utente;
import com.prezzipazzi.database.Database;
import com.prezzipazzi.manager.ManagerAutenticazione;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruggerotammaro
 */
public class NewEmptyJUnitTest extends DatabaseTestCase  {
     private FlatXmlDataSet loadedDataSet;
	private Connection jdbcConnection;

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
       Class.forName("com.mysql.jdbc.Driver");
        jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/DbPrezziPazzi", "root", "");
		return new DatabaseConnection(jdbcConnection);
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


