package bg.tu_varna.sit.commands.playHistoryCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;
import bg.tu_varna.sit.util.DateTimeParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TopTracksCommand extends Command {
    private PlayHistoryActions playHistoryActions;
    public TopTracksCommand(PlayHistoryActions playHistoryActions) {
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
        Map<Song, Integer> topTracks;
        StringBuilder sb = new StringBuilder("Top Tracks");

        int n;
        try {
            n = Integer.parseInt(args.getFirst());
        } catch (NumberFormatException e) {
            throw new CommandException("N must be a number");
        }
        LocalDateTime fromFilter = parsedArgs.get("from") != null ? DateTimeParser.parse(parsedArgs.get("from")) : null;
        LocalDateTime toFilter = parsedArgs.get("to") != null ? DateTimeParser.parse(parsedArgs.get("to")) : null;
        topTracks = playHistoryActions.topTracks(n, fromFilter, toFilter);

        if (fromFilter != null) {
            sb.append(" From: ").append(DateTimeParser.format(fromFilter));
        }

        if (toFilter != null) {
            sb.append(" To: ").append(DateTimeParser.format(toFilter));
        }

        sb.append("\n");

        topTracks.forEach((artist, count) ->
            sb.append(artist).append(" - Number of plays: ").append(count).append("\n")
        );
        return sb.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда топ n най-слушани песни за даден период.\n" +
                "   Usage: toptracks <n> [from=<date>] [to=<date>]";
    }
}
