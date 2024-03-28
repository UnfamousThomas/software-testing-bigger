package ee.ut.math;

import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import org.junit.Test;
import static org.junit.Assert.*;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;

public class SoldItemTest {

	@Test(timeout = 4000)
	public void testGetId() throws Throwable {
	    StockItem stockItem0 = new StockItem();
	    SoldItem soldItem0 = new SoldItem(stockItem0, 389);
	    soldItem0.getId();
	    assertEquals(389, (int) soldItem0.getQuantity());
	}

	@Test(timeout = 4000)
	public void testSetId() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    Long long0 = 0L;
	    soldItem0.setId(long0);
	    assertEquals(0.0, soldItem0.getPrice(), 0.01);
	}

	@Test(timeout = 4000)
	public void testGetName() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    String string0 = soldItem0.getName();
	    assertNull(string0);
	}

	@Test(timeout = 4000)
	public void testGetPrice() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    double double0 = soldItem0.getPrice();
	    assertEquals(0.0, double0, 0.01);
	}

	@Test(timeout = 4000)
	public void testToString() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    String string0 = soldItem0.toString();
	    assertEquals("SoldItem{id=null, name='null'}", string0);
	}

	@Test(timeout = 4000)
	public void testGetStockItem() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    StockItem stockItem0 = soldItem0.getStockItem();
	    assertNull(stockItem0);
	}

	@Test(timeout = 4000)
	public void testSetPrice() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    soldItem0.setPrice(245.82064175486);
	    assertEquals(245.82064175486, soldItem0.getPrice(), 0.01);
	}

	@Test(timeout = 4000)
	public void testSetQuantity() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    Integer integer0 = new Integer((-2191));
	    soldItem0.setQuantity(integer0);
	    assertNull(soldItem0.getId());
	}

	@Test(timeout = 4000)
	public void testGetSum() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    // Undeclared exception!
	    soldItem0.getSum();
	}

	@Test(timeout = 4000)
	public void testSetName() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    soldItem0.setName((String) null);
	    assertNull(soldItem0.getName());
	}

	@Test(timeout = 4000)
	public void testGetQuantity() throws Throwable {
	    StockItem stockItem0 = new StockItem();
	    SoldItem soldItem0 = new SoldItem(stockItem0, 1362);
	    Integer integer0 = soldItem0.getQuantity();
	    assertEquals(1362, (int) integer0);
	}

	@Test(timeout = 4000)
	public void testSetStockItem() throws Throwable {
	    SoldItem soldItem0 = new SoldItem();
	    StockItem stockItem0 = new StockItem();
	    soldItem0.setStockItem(stockItem0);
	    assertNull(soldItem0.getQuantity());
	}

}