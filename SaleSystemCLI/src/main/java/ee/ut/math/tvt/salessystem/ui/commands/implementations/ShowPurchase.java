package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

import java.util.List;

public class ShowPurchase extends ConsoleCommand {
    private final SalesSystemDAO dao;

    public ShowPurchase(SalesSystemDAO dao) {
        super("gp");
        this.dao = dao;
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        String id = args[1];
        if (dao.getPurchaseById(id) != null) {
            List<SoldItem> soldItemsFromPurchase = dao.getPurchaseById(id).getPurchasedItems();
            for (SoldItem soldItem : soldItemsFromPurchase) {
                System.out.println(soldItem);
            }
        } else {
            System.out.println("Purchase not found!");
        }
    }

}
