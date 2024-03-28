package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class SubmitPurchaseCommand extends ConsoleCommand {


    public SubmitPurchaseCommand() {
        super("p");
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        if(cart.getAll().isEmpty()) {
            System.out.println("Cart is empty. Can not complete empty purchase.");
            return;
        }
        cart.submitCurrentPurchase();
    }


}
