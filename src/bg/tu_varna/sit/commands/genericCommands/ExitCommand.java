package bg.tu_varna.sit.commands.genericCommands;

import bg.tu_varna.sit.commands.Command;

import java.util.List;

public class ExitCommand implements Command {
    public ExitCommand() {}

    @Override
    public String execute(List<String> args) {
        System.exit(0);
        return null; // null since the program is exiting, there is no need to return anything.
    }

    public String cmdHelpMessage() {
        return "Прекратява програмата.\n" +
                "   Usage: exit";
    }
}
