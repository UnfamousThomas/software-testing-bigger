package ee.ut.math.tvt.salessystem.ui;

import ee.ut.math.tvt.salessystem.dao.HibernateSalesSystemDAO;
import ee.ut.math.tvt.salessystem.dao.InMemorySalesSystemDAO;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.CommandManager;
import ee.ut.math.tvt.salessystem.ui.commands.implementations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple CLI (limited functionality).
 */
public class ConsoleUI {

    private final SalesSystemDAO dao;
    private final ShoppingCart cart;

    public ConsoleUI(SalesSystemDAO dao) {
        this.dao = dao;
        cart = new ShoppingCart(dao);
    }

    public static void main(String[] args) throws Exception {
        SalesSystemDAO dao = new InMemorySalesSystemDAO();
        ConsoleUI console = new ConsoleUI(dao);
        console.run();
    }

    /**
     * Run the sales system CLI.
     */
    public void run() throws IOException {
        System.out.println("===========================");
        System.out.println("=       Sales System      =");
        System.out.println("===========================");
        printUsage();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        CommandManager commandManager = new CommandManager();
        commandManager.registerCommands(
                new AddToCartCommand(dao),
                new AddWarehouseCommand(dao),
                new CartCommand(),
                new ClearCartCommand(),
                new HelpCommand(),
                new NewWarehouseItemCommand(dao),
                new PurchaseHistoryBetweenDatesCommand(dao),
                new PurchaseHistoryCommand(dao),
                new PurchaseHistoryLastTenCommand(dao),
                new QuitCommand(),
                new RemoveCartCommand(),
                new ShowPurchase(dao),
                new ShowTeamCommand(),
                new SubmitPurchaseCommand(),
                new WarehouseCommand(dao)
        );
        while (true) {
            System.out.print("> ");
            processCommand(commandManager, in.readLine().trim().toLowerCase());
        }
    }


    private void printUsage() {
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



    private void processCommand(CommandManager commandManager, String command) {
        commandManager.handleCommand(command, cart);
    }


}
