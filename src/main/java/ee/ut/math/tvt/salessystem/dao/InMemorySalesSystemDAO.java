package ee.ut.math.tvt.salessystem.dao;

import ee.ut.math.tvt.salessystem.dataobjects.Purchase;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InMemorySalesSystemDAO implements SalesSystemDAO {

    private final List<StockItem> stockItemList;
    private final List<SoldItem> soldItemList;

    private final List<Purchase> purchaseList;

    public InMemorySalesSystemDAO() {
        List<StockItem> items = new ArrayList<StockItem>();
        items.add(new StockItem(1L, "Lays chips", "Potato chips", 11.0, 5));
        items.add(new StockItem(2L, "Chupa-chups", "Sweets", 8.0, 8));
        items.add(new StockItem(3L, "Frankfurters", "Beer sauseges", 15.0, 12));
        items.add(new StockItem(4L, "Free Beer", "Student's delight", 0.0, 100));
        this.stockItemList = items;
        this.soldItemList = new ArrayList<>();
        this.purchaseList = new ArrayList<>();
        //Only for demo purposes
        List<SoldItem> soldItemsTest = new ArrayList<>();
        soldItemsTest.add(new SoldItem(new StockItem(1L, "Lays chips", "Potato chips", 11.0, 5), 3));
        for (int i = 0; i < 20; i++) {
            Purchase purchase = new Purchase(soldItemsTest, "Michael");
            purchaseList.add(purchase);
        }
        List<SoldItem> soldTest2 = new ArrayList<>();
        soldTest2.add(new SoldItem(new StockItem(2L, "Chupa-chups", "Sweets", 8.0, 8), 5));
        soldTest2.add(new SoldItem(new StockItem(1L, "Lays chips", "Potato chips", 11.0, 5), 3));
        Purchase testPurchase = new Purchase(soldTest2, "Mark");
        purchaseList.add(testPurchase);
    }

    @Override
    public List<StockItem> findStockItems() {
        return stockItemList;
    }

    @Override
    public StockItem findStockItem(long id) {
        for (StockItem item : stockItemList) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    @Override
    public void saveSoldItem(SoldItem item) {
        soldItemList.add(item);
    }

    @Override
    public void saveStockItem(StockItem stockItem) {
        stockItemList.add(stockItem);
    }

    @Override
    public void removeStockItem(StockItem stockItem) {
        if(stockItem == null) {
            throw new RuntimeException("Stockitem could not be deleted as it was null");
        }
        stockItemList.remove(stockItem);
    }

    @Override
    public void savePurchase(Purchase purchase) {
        purchaseList.add(purchase);
    }

    @Override
    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    @Override
    public List<Purchase> getLastTenPurchases() {
        if (purchaseList.size() < 10)
            return purchaseList;
        return purchaseList.subList(purchaseList.size() - 10, purchaseList.size());
    }

    @Override
    public List<Purchase> getPurchasesBetweenDates(LocalDateTime firstDate, LocalDateTime secondDate) {
        List<Purchase> copyOfPurchaseList = new ArrayList<>(purchaseList.stream().toList());
        copyOfPurchaseList.sort((o1, o2) -> o1.getDateAndTimeOfPurchase().compareTo(o2.getDateAndTimeOfPurchase()));
        List<Purchase> result = new ArrayList<>();
        for (Purchase purchase : copyOfPurchaseList) {
            LocalDateTime dateAndTimeOfPurchase = purchase.getDateAndTimeOfPurchase();
            if (!dateAndTimeOfPurchase.isAfter(secondDate) && !dateAndTimeOfPurchase.isBefore(firstDate))
                result.add(purchase);
        }
        if (!result.isEmpty())
            return result;
        return null;
    }

    @Override
    public Purchase getPurchaseById(String id) {
        for (Purchase purchase : purchaseList) {
            if (purchase.getId().equals(id))
                return purchase;
        }
        return null;
    }

    @Override
    public void beginTransaction() {
    }

    @Override
    public void rollbackTransaction() {
    }

    @Override
    public void commitTransaction() {
    }
}
