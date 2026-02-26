package bg.tu_varna.sit.commands;

import java.util.List;

public class ExitCommand implements Command{
    public ExitCommand() {}

    @Override
    public String execute(List<String> args) {
        System.out.println("Exiting...");
        System.exit(0);
        return null; // I dont need a value/string here since im exiting the program.
    }

    public String cmdHelpMessage() {
        return "Прекратява програмата.\n" +
                "   Usage: exit";
    }
}
