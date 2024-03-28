package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class QuitCommand extends ConsoleCommand {
    public QuitCommand() {
        super("q");
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        System.exit(0);
    }
}
