package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.Purchase;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class PurchaseHistoryCommand extends ConsoleCommand {
    private final SalesSystemDAO dao;

    public PurchaseHistoryCommand(SalesSystemDAO dao) {
        super("ph");
        this.dao = dao;
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        if (dao.getPurchaseList() == null || dao.getPurchaseList().isEmpty())
            System.out.println("There are no purchases!");
        else {
            for (Purchase purchase : dao.getPurchaseList()) {
                System.out.println(purchase.toString());
            }
        }
    }
}
