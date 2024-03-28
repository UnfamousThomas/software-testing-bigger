package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

import java.util.List;

public class WarehouseCommand extends ConsoleCommand {
    private final SalesSystemDAO dao;

    public WarehouseCommand(SalesSystemDAO dao) {
        super("w");
        this.dao = dao;
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        List<StockItem> stockItems = dao.findStockItems();
        System.out.println("-------------------------");
        for (StockItem si : stockItems) {
            System.out.println(si.getId() + " " + si.getName() + " " + si.getPrice() + "Euro (" + si.getQuantity() + " items)");
        }
        if (stockItems.isEmpty()) {
            System.out.println("\tNothing");
        }
        System.out.println("-------------------------");
    }
}
