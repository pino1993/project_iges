package junitdbunit;

import java.io.File;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dbunit.Assertion;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

public class DbUnit extends DBTestCase {
        
        private IDatabaseTester databaseTester;
        
	public DbUnit(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/dbprezzipazzi");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}

	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("testFile.xml"));
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE;
	}
        
	@Test 
	public void testUtenti() throws DataSetException, MalformedURLException, Exception {
                 // Fetch database data after executing your code
        
        IDataSet databaseDataSet = null;
        IDatabaseConnection connection = getConnection();
       
        databaseDataSet = connection.createDataSet();
                
           
        ITable actualTable = databaseDataSet.getTable("utenti");
        String emailActual = (String) actualTable.getValue(0, "Email_Utente");
        

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("utenti");
        String emailExpected = (String) expectedTable.getValue(1, "Email_Utente");
        // Assert actual database table match expected table
        //Assertion.assertEquals(expectedTable, actualTable);
        assertEquals(emailExpected, emailActual);
        assertEquals(actualTable.getValue(1, "Email_Utente"),"b@gmail.com");
        assertThat(actualTable.getValue(2,"Email_Utente"),is("c@gmail.com"));
        assertThat(actualTable.getValue(3, "Password"),is("4321"));
        assertThat(actualTable.getValue(2, "Password"),is("1234"));

        
	}
        
        @Test
        public void testProdottiAcquistati() throws Exception{
            IDataSet databaseDataSet = null;
            IDatabaseConnection connection = getConnection();
       
            databaseDataSet = connection.createDataSet();
                
           
            ITable actualTable = databaseDataSet.getTable("prodotti_acquistati");
            String emailActual = (String) actualTable.getValue(0, "Email_Utente");
        

            // Load expected data from an XML dataset
            IDataSet expectedDataSet = getDataSet();
            ITable expectedTable = expectedDataSet.getTable("prodotti_acquistati");
            String emailExpected = (String) expectedTable.getValue(0, "Email_Utente");
         // Assert actual database table match expected table
         //Assertion.assertEquals(expectedTable, actualTable);
            assertEquals(emailExpected, emailActual);
            assertThat(actualTable.getValue(2, "Id_Acquisto"),is(3));
            assertThat(actualTable.getValue(2, "Id_Offerte"),is(9));

            
	}
        @Test 
	public void testOfferte() throws DataSetException, MalformedURLException, Exception {
                 // Fetch database data after executing your code
        
        IDataSet databaseDataSet = null;
        IDatabaseConnection connection = getConnection();
       
        databaseDataSet = connection.createDataSet();
                
           
        ITable actualTable = databaseDataSet.getTable("offerte");
        String DesActual =(String) actualTable.getValue(2, "Descrizione");
        

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("offerte");
        String DesExpected =(String) expectedTable.getValue(0, "Descrizione");
        // Assert actual database table match expected table
        //Assertion.assertEquals(expectedTable, actualTable);
        assertEquals(DesExpected, DesActual);
        assertThat(actualTable.getValue(0, "Località"),is("Caraibi"));
        assertThat(actualTable.getValue(0, "tipo"),is("Vacanza"));
	}
        
        @Test
        public void testErrori() throws Exception {
            // Fetch database data after executing your code
        
        IDataSet databaseDataSet = null;
        IDatabaseConnection connection = getConnection();
       
        databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("prodotti_acquistati");
        String emailActual =(String) actualTable.getValue(0, "Email_Utente");
        
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("utenti");
        String emailExpected = (String) expectedTable.getValue(0, "Email_Utente");
        assertEquals(emailExpected,emailActual);
        ITable actualTable1 = databaseDataSet.getTable("offerte");
        String typeActual  =(String) actualTable1.getValue(0, "Tipo");
        ITable expectedTable1 = expectedDataSet.getTable("offerte");
        String typeExpected = (String) expectedTable.getValue(0, "Tipo");
        assertEquals(typeExpected,typeActual);
        
        }
}
