package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class CartCommand extends ConsoleCommand {
    public CartCommand() {
        super("c");
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        System.out.println("-------------------------");
        for (SoldItem si : cart.getAll()) {
            System.out.println(si.getName() + " " + si.getPrice() + "Euro (" + si.getQuantity() + " items)");
        }
        if (cart.getAll().isEmpty()) {
            System.out.println("\tNothing");
        }
        System.out.println("-------------------------");
    }
}
