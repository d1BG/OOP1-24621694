package bg.tu_varna.sit.commands.playHistoryCommands.topCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.util.ArgumentParser;
import bg.tu_varna.sit.util.DateTimeParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TopArtistsCommand extends Command {
    private PlayHistoryActions playHistoryActions;
    public TopArtistsCommand(PlayHistoryActions playHistoryActions) {
        this.playHistoryActions = playHistoryActions;
    }
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1, 3);

        List<String> newArgs = new ArrayList<>();
        for (int i = 1; i < args.size(); i++){ // grab any arg after the first one
            newArgs.add(args.get(i));
        }
        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(newArgs);
        Map<Artist, Integer> topArtists;
        StringBuilder sb = new StringBuilder("Top Artists");

        int n;
        try {
            n = Integer.parseInt(args.getFirst());
        } catch (NumberFormatException e) {
            throw new CommandException("N must be a number");
        }
        LocalDateTime fromFilter = parsedArgs.get("from") != null ? DateTimeParser.parse(parsedArgs.get("from")) : null;
        LocalDateTime toFilter = parsedArgs.get("to") != null ? DateTimeParser.parse(parsedArgs.get("to")) : null;
        topArtists = playHistoryActions.topArtists(n, fromFilter, toFilter);

        if (fromFilter != null) {
            sb.append(" From: ").append(DateTimeParser.format(fromFilter));
        }

        if (toFilter != null) {
            sb.append(" To: ").append(DateTimeParser.format(toFilter));
        }

        sb.append("\n");

        topArtists.forEach((artist, count) ->
            sb.append(artist).append(" - Number of plays: ").append(count).append("\n")
        );
        return sb.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда топ n изпълнители по слушания.\n" +
                "   Usage: topartists <n> [from=<date>] [to=<date>]";
    }
}
