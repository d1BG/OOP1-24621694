package bg.tu_varna.sit.commands;

import java.util.List;

public class ExitCommand implements Command{
    public ExitCommand() {}

    @Override
    public void execute(List<String> args) {
        System.out.println("Exiting...");
        System.exit(0);
    }

    public String cmdHelpMessage() {
        return "Прекратява програмата.\n" +
                "   Usage: exit";
    }
}
