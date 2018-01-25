/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.test;
import com.prezzipazzi.*;
import com.prezzipazzi.autenticazione.AutenticazioneServlet;
import com.prezzipazzi.autenticazione.AuthException;
import com.prezzipazzi.bean.Admin;
import com.prezzipazzi.bean.BeniDiConsumo;
import com.prezzipazzi.bean.Catalogo;
import com.prezzipazzi.bean.Cene;
import com.prezzipazzi.bean.PrestazioniOpera;
import com.prezzipazzi.bean.TipoUtente;
import com.prezzipazzi.bean.User;
import com.prezzipazzi.bean.Utente;
import com.prezzipazzi.bean.Vacanze;
import com.prezzipazzi.manager.ManagerAutenticazione;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
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
    public void TestUtente() throws SQLException, AuthException {
        User u = new User("test@email.it","test","testn","testc",0);
        ManagerAutenticazione m=new ManagerAutenticazione();
		assertEquals(0.0,u.getCredito(),0);
		assertEquals("testc",u.getCognome());
                assertEquals("test@email.it",u.getEmail());
                assertEquals("testn",u.getNome());
                assertEquals("test",u.getPassword());
                //da testare
                assertEquals("Email e/o password errate",m.loginUser("test@email.it", "test"));
                
    }
    @Test
    public void TestBeni(){
       BeniDiConsumo b=new BeniDiConsumo(1, 100,"prova.jpg", "Scarpe nike", 10, "Amazon", 4);
		assertEquals(b.getFornitore(),"Amazon");
		assertEquals(b.getGiudizio(),4);
		assertEquals(b.getDescrizione(),"Scarpe nike");
		assertEquals(b.getDaVendere(),10);
		assertEquals(b.eAcquistabile(),true);
		assertEquals(b.sconti(),false);	
		Catalogo c=new Catalogo();
		c.addOfferta(b);
		assertEquals(c.size(),1);
		assertEquals(c.dammiId(1),2);
		System.out.println(b.toString());
    }
    @Test
	public void testCena() throws Exception{
		
		Cene c=new Cene(13, 200,"cenaNapoli.jpg", "cena per 2 Salerno","Zio Michele", "Salerno", new GregorianCalendar(2018,0,24), 10);
		assertEquals(c.getDaVendere(),10);
		assertEquals(c.getLuogo(),"Salerno");
		assertEquals(c.getRistorante(),"Zio Michele");
		System.out.println(c.toString());
	}
        @Test
	public void testPrestazioniOpera() throws Exception{
		PrestazioniOpera p=new PrestazioniOpera(23,80,"spettacolo.jpg", "Napoli", "Spettacolo di Pinocchio","Compagnia Teatrale",5);
		assertEquals(p.eAcquistabile(),true);
		assertEquals(p.getFornitore(),"Compagnia Teatrale");
		assertEquals(p.getLocalita(),"Napoli");
		assertEquals(p.getProdVenduti(),0);
		assertEquals(p.sconti(),false);
		System.out.println(p.toString());	
	}
	@Test
	public void testVacanze(){
		Vacanze v= new Vacanze(34, 2000,"Caraibi.jpg", "Vacanza per 2 ai Caraibi","Caraibi",new GregorianCalendar(2018,3,24) , new GregorianCalendar(2018,2,24));
		assertEquals(v.eAcquistabile(),true);
		assertEquals(v.getLocalita(),"Caraibi");
		assertEquals(v.getPartenza(),new GregorianCalendar(2018,3,24));
		System.out.println(v.toString());
	}

}

