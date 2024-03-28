package ee.ut.math;

import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManualTest {

    @Test
    public void soldItemNameMatches() {
        String name = "Awesome item!";
        StockItem stockItem = new StockItem(1L, name, "", 1L, 1);
        SoldItem soldItem = new SoldItem(stockItem, 1);

        assertEquals(soldItem.getName(), name);
    }

    @Test
    public void newDescriptionMatches() {
        String betterDescription = "This is a description which is good";
        StockItem stockItem = new StockItem(5L, "a thing", "a description", 1L, 1);
        stockItem.setDescription(betterDescription);
        assertEquals(stockItem.getDescription(), betterDescription);
    }
}
