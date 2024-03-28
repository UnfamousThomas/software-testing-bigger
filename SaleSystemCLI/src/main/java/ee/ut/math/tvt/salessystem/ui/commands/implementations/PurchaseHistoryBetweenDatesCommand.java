package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.Purchase;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PurchaseHistoryBetweenDatesCommand extends ConsoleCommand {
    private final SalesSystemDAO dao;

    public PurchaseHistoryBetweenDatesCommand(SalesSystemDAO dao) {
        super("spbd");
        this.dao = dao;
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate firstDate = LocalDate.parse(args[1], formatter);
        LocalDate secondDate = LocalDate.parse(args[2], formatter);
        if (firstDate.isAfter(secondDate))
            System.out.println("Please enter smaller date first.");
        else
            showPurchasesBetweenDates(firstDate, secondDate);
    }

    private void showPurchasesBetweenDates(LocalDate firstDate, LocalDate secondDate) {
        LocalDateTime firstDateTime = firstDate.atStartOfDay();
        LocalDateTime secondDateTime = secondDate.atTime(23, 59, 59, 999999999);
        List<Purchase> purchases = dao.getPurchasesBetweenDates(firstDateTime, secondDateTime);
        if (purchases == null || purchases.isEmpty())
            System.out.println("There are no purchases!");
        else {
            for (Purchase purchase : purchases) {
                System.out.println(purchase.toString());
            }
        }
    }
}
