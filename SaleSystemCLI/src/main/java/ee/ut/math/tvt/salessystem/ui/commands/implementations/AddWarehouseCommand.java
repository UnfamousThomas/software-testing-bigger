package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class AddWarehouseCommand extends ConsoleCommand {

    private final SalesSystemDAO dao;

    public AddWarehouseCommand(SalesSystemDAO dao) {
        super("wa");
        this.dao = dao;
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        if(args.length == 3) {
            if(!isDouble(args[1])) {
                System.out.println("Invalid format");
                return;
            }
            int id = Integer.parseInt(args[1]);
            if(dao.findStockItem(id) == null) {
                System.out.println("Item with ID: " + id + " was not found in stock.");
            } else {
                if(!isDouble(args[2])) {
                    System.out.println("Invalid format");
                    return;
                }
                int amount = Integer.parseInt(args[2]);
                addToWarehouse(id, amount);
            }
        }
    }

    private void addToWarehouse(long id, int amount) {
        StockItem item = dao.findStockItem(id);

        if (amount > 10000) {
            System.out.println("That amount is too big, our warehouse will explode.");
            return;
        }
        if (amount < 1) {
            System.out.println("Not enough items to add to warehouse.");
            return;
        }

        if(item == null) {
            System.out.println("Item in stock, with id: " + id + " could not be found");
            return;
        }
        int toSet = item.getQuantity() + amount;
        dao.findStockItem(id).setQuantity(toSet);
    }
}
