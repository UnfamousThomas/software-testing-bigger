package meie.asi;

import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class KulmkappTest {

	@Test(timeout = 4000)
	public void testKasOnTühiReturningTrue() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp((-432));
	    boolean boolean0 = külmkapp0.kasOnTühi();
	    assertTrue(boolean0);
	}

	@Test(timeout = 4000)
	public void testCreatesKülmkappTaking3Arguments() throws Throwable {
	    LinkedList<Ese> linkedList0 = new LinkedList<Ese>();
	    Külmkapp külmkapp0 = new Külmkapp((-2092), linkedList0, new Date());
	}

	@Test(timeout = 4000)
	public void testNäitaKülmkappi0() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp((-1));
	    assertTrue(külmkapp0.kasOnTühi());
	    Date Date0 = new Date(2344, (-2254), (-2254));
	    Ese ese0 = new Ese("7", Date0, (-3699));
	    külmkapp0.lisaKülmkappi(ese0);
	    külmkapp0.näitaKülmkappi();
	    assertFalse(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testEemaldaKülmkapistHalvaksLäinudAndEemaldaKülmkapistHalvaksLäinud() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp((-1));
	    assertTrue(külmkapp0.kasOnTühi());
	    Ese ese0 = new Ese("7", new Date(), (-3699));
	    külmkapp0.lisaKülmkappi(ese0);
	    külmkapp0.eemaldaKülmkapistHalvaksLäinud();
	    assertFalse(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testEemaldaKülmkapistHalvaksLäinud() throws Throwable {
		Date Date0 = new Date();
	    Ese ese0 = new Ese("Ei mahtunud k\u00FClmikusse. S\u00F6\u00F6 \u00E4ra.", Date0, (-2240));
	    Külmkapp külmkapp0 = new Külmkapp((-2240));
	    külmkapp0.lisaKülmkappi(ese0);
	    // Undeclared exception!
	    külmkapp0.eemaldaKülmkapistHalvaksLäinud();
	}

	@Test(timeout = 4000)
	public void testNäitaKülmkappi1() throws Throwable {
		Date Date0 = new Date((-1866), 3623, (-1134));
	    Ese ese0 = new Ese("dd/MM/yyyy", Date0, (-3699));
	    Külmkapp külmkapp0 = new Külmkapp(1);
	    assertTrue(külmkapp0.kasOnTühi());
	    külmkapp0.lisaKülmkappi(ese0);
	    külmkapp0.näitaKülmkappi();
	    assertFalse(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testVõtaSuvalineEseReturningEseWhereGetKogusIsNegative() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp((-1));
		Date Date0 = new Date(2344, (-2254), (-2254));
	    Ese ese0 = new Ese("7", Date0, (-3699));
	    külmkapp0.lisaKülmkappi(ese0);
	    assertFalse(külmkapp0.kasOnTühi());
	    külmkapp0.võtaSuvalineEse();
	    assertTrue(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testLeiaEseNimetusegaReturningEseWhereGetKogusIsNegative() throws Throwable {
	    Ese ese0 = new Ese("k%oz", (Date) null, (-744));
	    List<Ese> list0 = List.of(ese0, ese0, ese0);
	    Külmkapp külmkapp0 = new Külmkapp((-744), list0, (Date) null);
	    Ese ese1 = külmkapp0.leiaEseNimetusega("k%oz");
	    assertEquals((-744), ese1.getKogus());
	}

	@Test(timeout = 4000)
	public void testLeiaEseNimetusegaReturningNull() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp(1);
	    assertTrue(külmkapp0.kasOnTühi());
		Date Date0 = new Date(2344, (-2254), (-2254));
	    Ese ese0 = new Ese("7", Date0, (-2254));
	    külmkapp0.lisaKülmkappi(ese0);
	    külmkapp0.leiaEseNimetusega("_rijE>UN.g9BKKtx[/");
	    assertFalse(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testUuendaEsemeKogust2() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp(1);
	    assertTrue(külmkapp0.kasOnTühi());
		Date Date0 = new Date(2344, (-2254), (-2254));
	    Ese ese0 = new Ese("7", Date0, (-3699));
	    külmkapp0.lisaKülmkappi(ese0);
	    külmkapp0.uuendaEsemeKogust("7", 1);
	    assertFalse(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testLisaKülmkappiWithEseWhereGetKogusIsZero() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp(1);
		Date Date0 = new Date(2344, 0, 0);
	    Ese ese0 = new Ese("7", Date0, 0);
	    külmkapp0.lisaKülmkappi(ese0);
	    külmkapp0.uuendaEsemeKogust("7", 0);
	    assertTrue(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testKasOnTühiReturningFalse() throws Throwable {
	    Date Date0 = new Date((-1866), 3623, (-1134));
	    Ese ese0 = new Ese("dd/MM/yyyy", Date0, (-3699));
	    Külmkapp külmkapp0 = new Külmkapp(1);
	    assertTrue(külmkapp0.kasOnTühi());
	    külmkapp0.lisaKülmkappi(ese0);
	    boolean boolean0 = külmkapp0.kasOnTühi();
	    assertFalse(boolean0);
	}

	@Test(timeout = 4000)
	public void testTühjendaKülmkapp() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp(1382);
	    külmkapp0.tühjendaKülmkapp();
	    assertTrue(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testUuendaEsemeKogust1() throws Throwable {
		Date Date0 = new Date((-1866), (-1866), (-1866), (-1866), (-1866), (-1866));
	    Ese ese0 = new Ese("dd/MM/yyyy", Date0, (-1866));
	    Külmkapp külmkapp0 = new Külmkapp((-1866));
	    assertTrue(külmkapp0.kasOnTühi());
	    külmkapp0.lisaKülmkappi(ese0);
	    külmkapp0.uuendaEsemeKogust("dd/MM/yyyy", 879);
	    assertFalse(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testVõtaSuvalineEseReturningNull() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp(2395);
	    külmkapp0.võtaSuvalineEse();
	    assertTrue(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testLisaKülmkappiWithEseWhereGetKogusIsPositive() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp(0);
	    Date Date0 = new Date();
	    Ese ese0 = new Ese("TsWEwT9,\"f/A;", Date0, 1);
	    külmkapp0.lisaKülmkappi(ese0);
	    assertTrue(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testUuendaEsemeKogust0() throws Throwable {
	    Külmkapp külmkapp0 = new Külmkapp((-1));
	    assertTrue(külmkapp0.kasOnTühi());
	    Date Date0 = new Date(2344, (-2254), (-2254));
	    Ese ese0 = new Ese("7", Date0, (-3699));
	    külmkapp0.lisaKülmkappi(ese0);
	    külmkapp0.uuendaEsemeKogust("e:", 2344);
	    assertFalse(külmkapp0.kasOnTühi());
	}

	@Test(timeout = 4000)
	public void testSalvestaKülmkapp() throws Throwable {
	    Date Date0 = new Date(2772, 0, 2772, 0, 0);
	    Ese ese0 = new Ese("dv\"", Date0, 0);
	    List<Ese> list0 = List.of(ese0, ese0, ese0, ese0);
	    Külmkapp külmkapp0 = new Külmkapp(2772, list0, Date0);
	    külmkapp0.salvestaKülmkapp("dv\"");
	    assertTrue(külmkapp0.kasOnTühi());
	}

}