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
        User u = new User("test@prezzipazzi.com","1234","Test","test");
		assertEquals(0.0,u.getCredito(),0);
		//eliminare user dal file userLogin prima di eseguire
		//assertEquals(1,u.login("no", "nuovo", "user"));
		//ritorna 0 se l'utente già esiste
		//assertEquals(0,u.login("no", "Andrea", "Barney"));
		//assertEquals("Saldo insufficiente",u.acquista(8, a.getCat(), 1));
    }
}
