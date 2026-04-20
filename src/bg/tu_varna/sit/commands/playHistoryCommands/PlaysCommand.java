package bg.tu_varna.sit.commands.playHistoryCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;
import bg.tu_varna.sit.util.DateTimeParser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PlaysCommand extends Command {
    MusicPlaylists musicPlaylists;
    public PlaysCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за извеждане на висчки пускания от историята
     * @param args аргументи които командата приема
     * @return всички пускания от историята
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 0, 4);

        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(args);

        Song songFilter;
        try {
            songFilter = parsedArgs.get("song") != null ?
                    musicPlaylists.getSongActions().getSongById(
                            Integer.parseInt(parsedArgs.get("song"))
                    ) : null;
        } catch (NumberFormatException e) {
            throw new CommandException(e.getMessage());
        }

        LocalDateTime toFilter = parsedArgs.get("to") != null ?
                DateTimeParser.parse(parsedArgs.get("to")) : null;

        Playlist playlistFilter = parsedArgs.get("playlist") != null ?
                musicPlaylists.getPlaylistActions().getPlaylistByName(parsedArgs.get("playlist")) : null;

        LocalDateTime fromFilter = parsedArgs.get("from") != null ?
                DateTimeParser.parse(parsedArgs.get("from")) : null;

        List<PlayHistoryEntry> filteredList =
                musicPlaylists.getPlayHistoryActions().filterEntries(fromFilter, toFilter, playlistFilter, songFilter);

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
