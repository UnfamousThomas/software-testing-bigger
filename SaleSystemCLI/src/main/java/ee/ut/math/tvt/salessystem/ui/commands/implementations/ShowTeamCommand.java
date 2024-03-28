package ee.ut.math.tvt.salessystem.ui.commands.implementations;

import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import ee.ut.math.tvt.salessystem.ui.commands.ConsoleCommand;

import java.util.ResourceBundle;

public class ShowTeamCommand extends ConsoleCommand {
    ResourceBundle resources = ResourceBundle.getBundle("application");

    public ShowTeamCommand() {
        super("t");
    }

    @Override
    public void onCommand(String[] args, ShoppingCart cart) {

        System.out.println("-------------------------");
        System.out.println("Team name: " + resources.getString("teamName"));
        System.out.println("Team leader: " + resources.getString("teamLeader"));
        System.out.println("Team leader email: " + resources.getString("teamLeaderEmail"));
        System.out.println("Team contact person: " + resources.getString("teamContactPerson"));
        System.out.println("Team members: " + resources.getString("teamMembers"));
        System.out.println("-------------------------");
    }
}
