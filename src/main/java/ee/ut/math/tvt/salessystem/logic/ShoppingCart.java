package ee.ut.math.tvt.salessystem.logic;

import ee.ut.math.tvt.salessystem.OutOfStockException;
import ee.ut.math.tvt.salessystem.SalesSystemException;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.Purchase;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {


    private final SalesSystemDAO dao;
    private final List<SoldItem> items = new ArrayList<>();
    private static final Logger log = LogManager.getLogger(ShoppingCart.class);

    public ShoppingCart(SalesSystemDAO dao) {
        this.dao = dao;
    }

    /**
     * Add new SoldItem to table.
     */
    public void addItem(SoldItem item) {
        // TODO verify that warehouse items' quantity remains at least zero or throw an exception
        if (item.getQuantity() < 0) {
            throw new NumberFormatException();
        }
        if (dao.findStockItem(item.getStockItem().getId()).getQuantity() >= 1) {
            item.setId(item.getStockItem().getId());
            boolean inCart = false;
            for (SoldItem soldItem : items) {
                if (Objects.equals(soldItem.getStockItem().getId(), item.getStockItem().getId())) {
                    inCart = true;
                    break;
                }
            }
            //item.setQuantity(Math.min(item.getQuantity(), item.getStockItem().getQuantity()));
            if (item.getQuantity() > item.getStockItem().getQuantity()) {
                throw new SalesSystemException();
            }
            if (!inCart) {
                items.add(item);
            }
            else {
                for (SoldItem soldItem : items) {
                    if (Objects.equals(soldItem.getStockItem().getId(), item.getStockItem().getId()))
                        soldItem.setQuantity(item.getQuantity());
                }
            }
            log.debug("Added " + item.getName() + " quantity of " + item.getQuantity());
        } else {
            log.debug("Product is out of stock.");
            throw new OutOfStockException("This product is out of stock.");
        }
    }

    public List<SoldItem> getAll() {
        return items;
    }

    public void removeItem(SoldItem item) {
        items.remove(item);
    }
    public void cancelCurrentPurchase() {
        items.clear();
    }

    public void submitCurrentPurchase() {

        // note the use of transactions. InMemorySalesSystemDAO ignores transactions
        // but when you start using hibernate in lab5, then it will become relevant.
        // what is a transaction? https://stackoverflow.com/q/974596
        try {
            dao.beginTransaction();
            for (SoldItem item : items) {
                dao.saveSoldItem(item);
                log.debug("Saved item: " + item);
                StockItem stockItem = dao.findStockItem(item.getStockItem().getId());
                stockItem.setQuantity(stockItem.getQuantity() - item.getQuantity());
                //dao.beginTransaction();
                dao.saveStockItem(stockItem);
                //dao.commitTransaction();
                log.debug("Removed purchased items from stock");
            }
            //dao.commitTransaction();
            List<SoldItem> soldItems = new ArrayList<>(items);
            Purchase purchase = new Purchase(soldItems, "Michael");
            //dao.beginTransaction();
            dao.savePurchase(purchase);
            dao.commitTransaction();
            log.debug("Saved all items from purchase.");
            items.clear();
        } catch (Exception e) { //TODO EI SEE ON VÄGA HALB. ÄRA MITTE KUNAGI CATCHI LIC EXCEPTIONIT PALUN.

            log.error("Error completing purchase, rollbacked: " + e);
            throw e;
        }
    }

    public void removeAllFromCart(long id) {
        SoldItem soldItem = null;
        for (SoldItem si : getAll()) {
            if(si.getStockItem().getId().equals(id)) soldItem = si;
        }
        if(soldItem != null) {
            log.debug("Removed item from cart: " + soldItem.getName());
            removeItem(soldItem);
        }
    }

    public void removeAmountFromCart(long id, int amount) {
        SoldItem soldItem = null;
        for (SoldItem si : getAll()) {
            if(si.getStockItem().getId().equals(id)) soldItem = si;
        }
        if(soldItem == null) return;
        soldItem.setQuantity(soldItem.getQuantity() - amount);
        if(soldItem.getQuantity() <= 0) {
            removeItem(soldItem);
        }
    }
}
