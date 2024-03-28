package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class NewWarehouseItemCommand extends ConsoleCommand {
    private final SalesSystemDAO dao;

    public NewWarehouseItemCommand(SalesSystemDAO dao) {
        super("wn");
        this.dao = dao;
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        if (args.length >= 5) {
            //todo add errors for invalid numbers
            if (!isDouble(args[1]) || !isDouble(args[2]) || !isDouble(args[3])) {
                System.out.println("Invalid format");
                return;
            }
            int id = Integer.parseInt(args[1]);
            int amount = Integer.parseInt(args[2]);
            double price = Double.parseDouble(args[3]);
            StringBuilder name = new StringBuilder();
            for (int i = 4; i < args.length; i++) {
                name.append(args[i]).append(" ");
            }
            createNewInWarehouse(id, amount, price, name.toString().trim());
        } else {
            System.out.println("Invalid format for command.");
        }
    }

    public void createNewInWarehouse(long id, int amount, double price, String name) {
        StockItem stockItem = new StockItem();
        if (dao.findStockItem(id) != null) {
            System.out.println("That id already exists in the warehouse.");
            return;
        }
        if (amount > 10000) {
            System.out.println("That amount is too big, our warehouse will explode.");
            return;
        }
        if (amount < 1) {
            System.out.println("Not enough items to add to warehouse.");
            return;
        }
        System.out.println(price);
        if(price < 0 || price > 1000000) {
            System.out.println("Price is too big or too small.");
            return;
        }

        stockItem.setId(id);
        stockItem.setQuantity(amount);
        stockItem.setPrice(price);
        stockItem.setName(name);
        dao.beginTransaction();
        dao.saveStockItem(stockItem);
        dao.commitTransaction();
    }
}
