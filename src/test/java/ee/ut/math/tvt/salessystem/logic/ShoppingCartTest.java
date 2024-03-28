package ee.ut.math.tvt.salessystem.logic;

import ee.ut.math.tvt.salessystem.SalesSystemException;
import ee.ut.math.tvt.salessystem.dao.InMemorySalesSystemDAO;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShoppingCartTest {
    @Test
    public void testAddingExistingItem() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        dao.saveStockItem(stockitem);
        SoldItem solditem = new SoldItem(stockitem, 1);
        SoldItem solditem2 = new SoldItem(stockitem, 2);
        shoppingCart.addItem(solditem);
        Assertions.assertEquals(1, shoppingCart.getAll().get(0).getQuantity());
        shoppingCart.addItem(solditem2);
        Assertions.assertEquals(2, shoppingCart.getAll().get(0).getQuantity());
    }
    @Test
    public void testAddingNewItem() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        StockItem stockitem2 = new StockItem(11L, "Test item2", "", 10, 10);
        dao.saveStockItem(stockitem);
        dao.saveStockItem(stockitem2);
        SoldItem solditem = new SoldItem(stockitem, 1);
        SoldItem solditem2 = new SoldItem(stockitem2, 2);
        shoppingCart.addItem(solditem);
        assertEquals(1, shoppingCart.getAll().size());
        assertTrue(shoppingCart.getAll().contains(solditem));
        shoppingCart.addItem(solditem2);
        assertEquals(2, shoppingCart.getAll().size());
        assertTrue(shoppingCart.getAll().contains(solditem2));
    }
    @Test
    public void testAddingItemWithNegativeQuantity() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        dao.saveStockItem(stockitem);
        SoldItem solditem = new SoldItem(stockitem, -1);
        Assertions.assertThrows(NumberFormatException.class, () ->
                shoppingCart.addItem(solditem));
    }
    @Test
    public void testAddingItemWithQuantityTooLarge() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        dao.saveStockItem(stockitem);
        SoldItem solditem = new SoldItem(stockitem, 20);
        Assertions.assertThrows(SalesSystemException.class, () ->
                shoppingCart.addItem(solditem));
    }
    @Test
    public void testSubmittingCurrentPurchaseDecreasesStockItemQuantity() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        dao.saveStockItem(stockitem);
        SoldItem solditem = new SoldItem(stockitem, 1);
        shoppingCart.addItem(solditem);
        shoppingCart.submitCurrentPurchase();
        Assertions.assertEquals(9, dao.findStockItem(10L).getQuantity());
    }
    @Test
    public void testSubmittingCurrentPurchaseBeginsAndCommitsTransaction() {
        SalesSystemDAO dao = mock(InMemorySalesSystemDAO.class);
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        when(dao.findStockItem(10L)).thenReturn(stockitem);
        dao.saveStockItem(stockitem);
        SoldItem solditem = new SoldItem(stockitem, 1);
        shoppingCart.addItem(solditem);
        shoppingCart.submitCurrentPurchase();
        verify(dao, times(1)).beginTransaction();
        verify(dao, times(1)).commitTransaction();
    }
    @Test
    public void testSubmittingCurrentOrderCreatesHistoryItem() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        dao.saveStockItem(stockitem);
        SoldItem solditem = new SoldItem(stockitem, 1);
        shoppingCart.addItem(solditem);
        shoppingCart.submitCurrentPurchase();
        assertTrue(dao.getPurchaseList().get(dao.getPurchaseList().size()-1).getPurchasedItems().contains(solditem));
    }
    @Test
    public void testSubmittingCurrentOrderSavesCorrectTime() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        dao.saveStockItem(stockitem);
        SoldItem solditem = new SoldItem(stockitem, 1);
        shoppingCart.addItem(solditem);
        LocalDateTime time = LocalDateTime.now();
        shoppingCart.submitCurrentPurchase();
        LocalDateTime purchaseTime = dao.getPurchaseList().get(dao.getPurchaseList().size()-1).getDateAndTimeOfPurchase();
        assertEquals(time.getYear(), purchaseTime.getYear());
        assertEquals(time.getDayOfYear(), purchaseTime.getDayOfYear());
        assertEquals(time.getHour(), purchaseTime.getHour());
        assertEquals(time.getMinute(), purchaseTime.getMinute());
    }
    @Test
    public void testCancellingOrder () {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        StockItem stockitem = new StockItem(10L, "Test item", "", 10, 10);
        StockItem stockitem2 = new StockItem(11L, "Test item2", "", 100, 10);
        dao.saveStockItem(stockitem);
        dao.saveStockItem(stockitem2);
        SoldItem solditem = new SoldItem(stockitem, 1);
        SoldItem solditem2 = new SoldItem(stockitem2, 1);
        shoppingCart.addItem(solditem);
        shoppingCart.cancelCurrentPurchase();
        shoppingCart.addItem(solditem2);
        shoppingCart.submitCurrentPurchase();
        Assertions.assertEquals(10, dao.findStockItem(10L).getQuantity());
        Assertions.assertEquals(9, dao.findStockItem(11L).getQuantity());
    }

}