package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class ClearCartCommand extends ConsoleCommand {
    public ClearCartCommand() {
        super("r");
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        cart.cancelCurrentPurchase();
    }
}
