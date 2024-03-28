package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class RemoveCartCommand extends ConsoleCommand {
    public RemoveCartCommand() {
        super("rc");
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        if(args.length >= 2 && args.length < 4) {
            if(!isLong(args[1])) {
                System.out.println("Invalid format");
                return;
            }
            long id = Long.parseLong(args[1]);
            if(args.length == 3) {
                if(!isDouble(args[2])) {
                    System.out.println("Invalid format");
                    return;
                }
                int amount = Integer.parseInt(args[2]);
                cart.removeAmountFromCart(id, amount);
            } else {
                cart.removeAllFromCart(id);
            }
        }
    }
}
