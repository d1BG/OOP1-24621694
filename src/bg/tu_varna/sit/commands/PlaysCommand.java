package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;
import bg.tu_varna.sit.util.DateTimeParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaysCommand implements Command {
    MusicPlaylists musicPlaylists;
    public PlaysCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() > 4) {
            throw new CommandException("Invalid arguments");
        }

        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(args);
        List<PlayHistoryEntry> filteredList = new ArrayList<>();

        try {
            // TODO: ADD MORE ERROR HANDLING!!!

            LocalDateTime fromFilter = parsedArgs.get("from") != null ? DateTimeParser.parse(parsedArgs.get("from")) : null;
            LocalDateTime toFilter = parsedArgs.get("to") != null ? DateTimeParser.parse(parsedArgs.get("to")) : null;
            Song songFilter = parsedArgs.get("song") != null ? musicPlaylists.getSongActions().getSong(Integer.parseInt(parsedArgs.get("song"))) : null;
            Playlist playlistFilter = parsedArgs.get("playlist") !=null ? musicPlaylists.getPlaylistActions().getPlaylistByName(parsedArgs.get("playlist")) : null;
            filteredList = musicPlaylists.getPlayHistoryActions().plays(fromFilter, toFilter, playlistFilter, songFilter);
        } catch (NumberFormatException e) {
            throw new CommandException(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        for (PlayHistoryEntry e : filteredList) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Справка за слушанията според зададени критерии.\n" +
                "   Usage: plays [from=<date>] [to=<date>] [playlist=<name>] [song=<songId>]";
    }
}
