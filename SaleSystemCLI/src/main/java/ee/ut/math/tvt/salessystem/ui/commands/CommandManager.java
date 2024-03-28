package ee.ut.math.tvt.salessystem.ui.commands;

import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private static final Logger log = LogManager.getLogger(CommandManager.class);
    private Map<String, ConsoleCommand> commands = new HashMap<>();

    public void registerCommand(ConsoleCommand command) {
        commands.put(command.getCommand(), command);
    }

    public void registerCommands(ConsoleCommand... commands) {
        for (ConsoleCommand command : commands) {
            registerCommand(command);
        }
    }

    public void handleCommand(String input, ShoppingCart cart) {
        String[] args = input.split(" ");
        String command = args[0].toLowerCase();
        if(commands.containsKey(command)) {
            log.log(Level.DEBUG, "User issued command: " + command);
            commands.get(command).onCommand(args, cart);
        }
        else {
            log.log(Level.DEBUG, "User issued invalid command: " + command);
            System.out.println("Invalid command. Try again!");
        }
    }
}
