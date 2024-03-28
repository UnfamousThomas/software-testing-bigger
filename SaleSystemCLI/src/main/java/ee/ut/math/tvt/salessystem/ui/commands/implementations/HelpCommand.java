package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

public class HelpCommand extends ConsoleCommand {
    public HelpCommand() {
        super("h");
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {
        System.out.println("-------------------------");
        System.out.println("Usage:");
        System.out.println("h\t\t\tShow this help");
        System.out.println("w\t\t\tShow warehouse contents");
        System.out.println("wn\tI A P N\tAdd A new items to the warehouse with ID I, name N and price P.");
        System.out.println("wa\tI A\t\tAdd A of specific I id item to the warehouse");
        System.out.println("c\t\t\tShow cart contents");
        System.out.println("rc\tI A \tRemove item with ID I from cart if no A is provided all items with that ID are removed");
        System.out.println("a\tIDX NR\tAdd NR of stock item with index IDX to the cart");
        System.out.println("p\t\t\tPurchase the shopping cart");
        System.out.println("r\t\t\tReset the shopping cart");
        System.out.println("t\t\t\tShow team information");
        System.out.println("ph\t\t\tShow purchase history");
        System.out.println("hlt\t\t\tShow last 10 purchases");
        System.out.println("spbd\tfirst date (dd.mm.yyyy)\tsecond date (dd.mm.yyyy)\tShow purchases between dates");
        System.out.println("gp\ti\t\tGet purchase by ID i");
        System.out.println("q\t\t\tQuit the application");

        System.out.println("-------------------------");
    }
}
