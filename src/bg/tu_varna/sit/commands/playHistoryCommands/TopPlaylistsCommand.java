package bg.tu_varna.sit.commands.playHistoryCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.util.ArgumentParser;
import bg.tu_varna.sit.util.DateTimeParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TopPlaylistsCommand extends Command {
    private PlayHistoryActions playHistoryActions;
    public TopPlaylistsCommand(PlayHistoryActions playHistoryActions) {
        this.playHistoryActions = playHistoryActions;
    }
    @Override
    public String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1, 3);

        List<String> newArgs = new ArrayList<>();
        for (int i = 1; i < args.size(); i++){ // grab any arg after the first one
            newArgs.add(args.get(i));
        }
        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(newArgs);
        Map<Playlist, Integer> topPlaylists;
        StringBuilder sb = new StringBuilder("Top Playlists");

        int n;
        try {
            n = Integer.parseInt(args.getFirst());
        } catch (NumberFormatException e) {
            throw new CommandException("N must be a number");
        }
        LocalDateTime fromFilter = parsedArgs.get("from") != null ? DateTimeParser.parse(parsedArgs.get("from")) : null;
        LocalDateTime toFilter = parsedArgs.get("to") != null ? DateTimeParser.parse(parsedArgs.get("to")) : null;
        topPlaylists = playHistoryActions.topPlaylists(n, fromFilter, toFilter);

        if (fromFilter != null) {
            sb.append(" From: ").append(DateTimeParser.format(fromFilter));
        }

        if (toFilter != null) {
            sb.append(" To: ").append(DateTimeParser.format(toFilter));
        }

        sb.append("\n");

        topPlaylists.forEach((artist, count) ->
            sb.append(artist).append(" - Number of plays: ").append(count).append("\n")
        );
        return sb.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда топ n най-активни плейлисти.\n" +
                "   Usage: topplaylists <n> [from=<date>] [to=<date>]";
    }
}
