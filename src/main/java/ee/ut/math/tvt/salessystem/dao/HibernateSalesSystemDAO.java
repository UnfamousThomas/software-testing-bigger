package ee.ut.math.tvt.salessystem.dao;

import ee.ut.math.tvt.salessystem.dataobjects.Purchase;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class HibernateSalesSystemDAO implements SalesSystemDAO {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private EntityTransaction transaction;

    private static final Logger log = LogManager.getLogger(HibernateSalesSystemDAO.class);



    public HibernateSalesSystemDAO() {
        // if you get ConnectException / JDBCConnectionException then you
        // probably forgot to start the database before starting the application
        emf = Persistence.createEntityManagerFactory("pos");
        em = emf.createEntityManager();
    }

    private  <T> List<T> getList(Class<T> entityClass) {
        String queryString = "FROM " + entityClass.getSimpleName();
        TypedQuery<T> query = em.createQuery(queryString, entityClass);
        return query.getResultList();
    }
    @Override
    public List<StockItem> findStockItems() {
        return getList(StockItem.class);
    }

    @Override
    public StockItem findStockItem(long id) {
        return em.find(StockItem.class, id);
    }

    @Override
    public void saveStockItem(StockItem stockItem) {
        em.persist(stockItem);
    }

    @Override
    public void removeStockItem(StockItem stockItem) {
        if(stockItem == null) {
            throw new RuntimeException("Stockitem could not be deleted as it was null");
        }
        beginTransaction();
        em.remove(stockItem);
        em.flush();
        commitTransaction();
    }

    @Override
    public void saveSoldItem(SoldItem item) {
        em.persist(item);
    }

    @Override
    public void savePurchase(Purchase purchase) {
        em.persist(purchase);
    }

    @Override
    public List<Purchase> getPurchaseList() {
        return getList(Purchase.class);
    }

    @Override
    public List<Purchase> getLastTenPurchases() {
        return em.createQuery("FROM Purchase ORDER BY dateAndTimeOfPurchase DESC", Purchase.class).setMaxResults(10).getResultList();
    }

    @Override
    public List<Purchase> getPurchasesBetweenDates(LocalDateTime firstDate, LocalDateTime secondDate) {
        String queryString = "FROM Purchase WHERE dateAndTimeOfPurchase BETWEEN :firstDate AND :secondDate ORDER BY dateAndTimeOfPurchase ASC";
        TypedQuery<Purchase> query = em.createQuery(queryString, Purchase.class);
        query.setParameter("firstDate", firstDate);
        query.setParameter("secondDate", secondDate);
        List<Purchase> result = query.getResultList();
        if (!result.isEmpty())
            return result;
        return null;
    }

    @Override
    public Purchase getPurchaseById(String id) {
        return em.find(Purchase.class, id);
    }

    @Override
    public void beginTransaction() {
        if (transaction == null || !transaction.isActive()) {
            transaction = em.getTransaction();
            transaction.begin();
        } else {
            log.log(Level.INFO, "Did not start new transaction as transaction was active.");
        }
    }
    @Override
    public void rollbackTransaction () {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        } else {
            log.log(Level.INFO,"No active transaction to rollback.");
        }
    }
    @Override
    public void commitTransaction() {
        try {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            } else {
                log.log(Level.INFO,"No active transaction to commit.");
            }
        } catch (RollbackException e) {
            rollbackTransaction();
        }
    }
}

