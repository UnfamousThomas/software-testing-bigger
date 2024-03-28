package ee.ut.math.tvt.salessystem.ui.commands;

import ee.ut.math.tvt.salessystem.logic.ShoppingCart;

public abstract class ConsoleCommand {

    public ConsoleCommand(String command) {
        this.command = command.toLowerCase();
    }

    private final String command;

    public abstract void onCommand(String[] args, ShoppingCart cart);

    public String getCommand() {
        return command;
    }

    protected boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
