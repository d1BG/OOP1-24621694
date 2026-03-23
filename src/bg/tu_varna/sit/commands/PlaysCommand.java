package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;
import java.util.Map;

public class PlaysCommand implements Command {
    PlayHistoryActions playHistoryActions;
    public PlaysCommand(PlayHistoryActions playHistoryActions) {
        this.playHistoryActions = playHistoryActions;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() > 4) {
            throw new CommandException("Invalid arguments");
        }

        if (args.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (PlayHistoryEntry entry : playHistoryActions.getEntries()) {
                sb.append(entry).append("\n");
            }
            return sb.toString();
        }

        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(args);
        return "unimplemented";
    }

    @Override
    public String cmdHelpMessage() {
        return "Справка за слушанията според зададени критерии.\n" +
                "   Usage: plays [from=<date>] [to=<date>] [playlist=<name>] [song=<songId>]";
    }
}
