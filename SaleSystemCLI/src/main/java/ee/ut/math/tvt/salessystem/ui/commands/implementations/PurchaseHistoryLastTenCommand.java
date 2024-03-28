package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.Purchase;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class PurchaseHistoryLastTenCommand extends ConsoleCommand {
    private final SalesSystemDAO dao;

    public PurchaseHistoryLastTenCommand(SalesSystemDAO dao) {
        super("hlt");
        this.dao = dao;
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        if (dao.getLastTenPurchases() == null || dao.getLastTenPurchases().isEmpty())
            System.out.println("There are no purchases!");
        else {
            for (Purchase purchase : dao.getLastTenPurchases()) {
                System.out.println(purchase.toString());
            }
        }
    }
}
