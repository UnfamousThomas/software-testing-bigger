package ee.ut.math.tvt.salessystem.ui.controllers;

import ee.ut.math.tvt.salessystem.SalesSystemException;
import ee.ut.math.tvt.salessystem.dao.InMemorySalesSystemDAO;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class StockControllerTest {

    @Test
    public void testAddingItemBeginsAndCommitsTransaction() {
        SalesSystemDAO dao = mock(SalesSystemDAO.class);
        StockController controller = new StockController(dao);
        controller.addProduct(10L, "Test item", "", 10, 10);
        verify(dao, times(1)).beginTransaction();
        verify(dao, times(1)).commitTransaction();
    }
    @Test
    public void testAddingNewItem() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        StockController controller = new StockController(dao);
        StockItem item = new StockItem(10L, "Test item", "", 10, 10);
        controller.addProduct(10L, "Test item", "", 10, 10);
        Assertions.assertEquals(dao.findStockItem(item.getId()).getName(), item.getName());
        Assertions.assertEquals(dao.findStockItem(item.getId()).getPrice(), item.getPrice());
        Assertions.assertEquals(dao.findStockItem(item.getId()).getQuantity(), item.getQuantity());
    }
    @Test
    public void testAddingExistingItem() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        StockController controller = new StockController(dao);
        controller.addProduct(10L, "Test item", "", 10, 10);
        controller.addProduct(10L, "Test item", "", 10, 10);
        Assertions.assertEquals(dao.findStockItem(10L).getQuantity(), 20);
    }
    @Test
    public void testAddingItemWithNegativePrice() {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        StockController controller = new StockController(dao);
        Assertions.assertThrows(SalesSystemException.class, () ->
                controller.addProduct(10L, "Test item", "", -10, 10));
    }
}