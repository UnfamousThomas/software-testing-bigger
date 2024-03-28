package ee.ut.math.tvt.salessystem.dataobjects;

import jdk.jfr.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity
public class Purchase {

    @Id
    private String id;
    @Timestamp
    private LocalDateTime dateAndTimeOfPurchase;
    @OneToMany
    private List<SoldItem> purchasedItems;
    private String cashier;
    private double totalCost;
    private int totalAmountOfProducts;
    private final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private String formattedDateAndTime;


    public Purchase(List<SoldItem> purchasedItems, String cashier) {
        this.id = UUID.randomUUID().toString();
        this.dateAndTimeOfPurchase = LocalDateTime.now();
        this.purchasedItems = purchasedItems;
        this.cashier = cashier;
        for (SoldItem item : purchasedItems) {
            totalCost += item.getSum();
            totalAmountOfProducts += item.getQuantity();
        }
        this.formattedDateAndTime = dateAndTimeOfPurchase.format(CUSTOM_FORMATTER);
    }

    public Purchase() {

    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateAndTimeOfPurchase() {
        return dateAndTimeOfPurchase;
    }

    public List<SoldItem> getPurchasedItems() {
        return purchasedItems;
    }

    public String getCashier() {
        return cashier;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getTotalAmountOfProducts() {
        return totalAmountOfProducts;
    }

    public String getFormattedDateAndTime() {
        return formattedDateAndTime;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", date and time: " +
                formattedDateAndTime + ", total cost: " + totalCost +
                ", total products: " + totalAmountOfProducts + ", cashier: " + cashier;
    }
}
