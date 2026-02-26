package bg.tu_varna.sit.commands;

import java.util.List;

public class ExitCommand implements Command{
    public ExitCommand() {}

    @Override
    public String execute(List<String> args) {
        return "Exiting...";
    }

    public String cmdHelpMessage() {
        return "Прекратява програмата.\n" +
                "   Usage: exit";
    }
}
