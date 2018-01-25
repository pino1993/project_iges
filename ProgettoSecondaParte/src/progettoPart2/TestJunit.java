package progettoPart2;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Test;

public class TestJunit {
	Admin a;
	private BeniDiConsumo b;
	private User u;
	private Cene c;
	private PrestazioniOpera p;
	private Vacanze v;
	

	@Test
	public void testUser() throws Exception {
		a = new Admin ();
		u = new User ("userLogin.txt");
		assertEquals(1,u.login("si", "Andrea", "Barney"));
		//eliminare user dal file userLogin prima di eseguire
		assertEquals(1,u.login("no", "nuovo", "user"));
		//ritorna 0 se l'utente già esiste
		assertEquals(0,u.login("no", "Andrea", "Barney"));
		assertEquals("Saldo insufficiente",u.acquista(8, a.getCat(), 1));
		
}
	@Test
	public void testBeni() throws Exception{
		b=new BeniDiConsumo(1, 100, "Cena per 2", 10, "Amazon", 4);
		assertEquals(b.getFornitore(),"Amazon");
		assertEquals(b.getGiudizio(),4);
		assertEquals(b.getDescrizione(),"Cena per 2");
		assertEquals(b.getDaVendere(),10);
		assertEquals(b.eAcquistabile(),true);
		assertEquals(b.sconti(),false);	
		Catalogo c=new Catalogo(true);
		c.addOfferta(b);
		assertEquals(c.size(),6);
		assertEquals(c.dammiId(1),2);
		System.out.println(b.toString());
	}
	
	@Test
	public void testCena() throws Exception{
		
		c=new Cene(13, 200, "Zio Michele", "cena per 2 Salerno", "Salerno", new GregorianCalendar(2018,0,24), 10);
		assertEquals(c.getDaVendere(),10);
		assertEquals(c.getLuogo(),"Salerno");
		assertEquals(c.getRistorante(),"Zio Michele");
		System.out.println(c.toString());
	}
	@Test
	public void testPrestazioniOpera() throws Exception{
		p=new PrestazioniOpera(23,80, "Cupertino", "Keynote Apple","Apple",5);
		assertEquals(p.eAcquistabile(),true);
		assertEquals(p.getFornitore(),"Apple");
		assertEquals(p.getLocalita(),"Cupertino");
		assertEquals(p.getProdVenduti(),0);
		assertEquals(p.sconti(),false);
		System.out.println(p.toString());	
	}
	@Test
	public void testVacanze(){
		v= new Vacanze(34, 2000, "Caraibi",new GregorianCalendar(2018,3,24) , new GregorianCalendar(2018,2,24));
		assertEquals(v.eAcquistabile(),true);
		assertEquals(v.getLocalita(),"Caraibi");
		assertEquals(v.getPartenza(),new GregorianCalendar(2018,3,24));
		System.out.println(v.toString());
	}
	@After
	public void rimuovi() throws EOFException, FileNotFoundException, ClassNotFoundException, IOException, Exception{
		/*a=new Admin();
		a.rmvProdotto(1);
		a.rmvProdotto(13);
		a.rmvProdotto(23);
		a.rmvProdotto(34);*/
	}

}
