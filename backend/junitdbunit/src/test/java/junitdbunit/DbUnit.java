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
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root");
	}

	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("user.xml"));
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
        
	@Test
	public void testById() throws DataSetException, MalformedURLException, Exception {
                 // Fetch database data after executing your code
        IDataSet databaseDataSet = null;
        IDatabaseConnection connection = getConnection();
       
        databaseDataSet = connection.createDataSet();
                
           
        ITable actualTable = databaseDataSet.getTable("utenti");
        String emailActual = (String) actualTable.getValue(0, "Email_Utente");
        

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("utenti");
        String emailExpected = (String) expectedTable.getValue(0, "Email_Utente");
        // Assert actual database table match expected table
        //Assertion.assertEquals(expectedTable, actualTable);
        assertEquals(emailExpected, emailActual);
        try
        {
            DatabaseOperation.CLEAN_INSERT.execute(connection, databaseDataSet);
        }
        finally
        {
            connection.close();
        }
	}

}
