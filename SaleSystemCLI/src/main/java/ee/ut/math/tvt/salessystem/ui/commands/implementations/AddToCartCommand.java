package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.SalesSystemException;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.NoSuchElementException;

public class AddToCartCommand extends ConsoleCommand {
    private final SalesSystemDAO dao;
    private static final Logger log = LogManager.getLogger(AddToCartCommand.class);


    public AddToCartCommand(SalesSystemDAO dao) {
        super("a");
        this.dao = dao;
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        if (args.length == 3) {
            try {
                if(!isLong(args[1]) || !isDouble(args[2])) {
                    System.out.println("Invalid input.");
                    return;
                }
                long idx = Long.parseLong(args[1]);
                int amount = Integer.parseInt(args[2]);
                StockItem item = dao.findStockItem(idx);
                if(amount < 1) {
                    System.out.println("Too little items to add");
                }
                else if(amount > 1000000) {
                    System.out.println("Too many items to add, store will run out of stuff.");
                }
                else if (item != null) {
                    cart.addItem(new SoldItem(item, Math.min(amount, item.getQuantity())));
                } else {
                    System.out.println("No stock item with ID: " + idx);
                }
            } catch (SalesSystemException | NoSuchElementException e) {
                log.error(e.getMessage(), e);
            }
        }

    }
}
