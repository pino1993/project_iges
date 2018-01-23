/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.test;
import com.prezzipazzi.*;
import com.prezzipazzi.autenticazione.AutenticazioneServlet;
import com.prezzipazzi.bean.TipoUtente;
import com.prezzipazzi.bean.User;
import com.prezzipazzi.bean.Utente;
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
public class JUnitTest {

    public JUnitTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestUtente() {
        User u = new User("test@email.it","test","testn","testc",0);
		assertEquals(0.0,u.getCredito(),0);
		assertEquals("testc",u.getCognome());
                assertEquals("test@email.it",u.getEmail());
                assertEquals("testn",u.getNome());
                assertEquals("test",u.getPassword());
                
    }
}
