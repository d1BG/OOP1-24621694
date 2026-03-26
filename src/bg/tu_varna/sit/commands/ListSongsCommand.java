package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.Genre;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;

import java.time.Year;
import java.util.List;
import java.util.Map;

public class ListSongsCommand implements Command {
    private SongActions songActions;
    private ArtistActions artistActions;
    public ListSongsCommand(SongActions songActions, ArtistActions artistActions) {
        this.songActions = songActions;
        this.artistActions = artistActions;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() > 3) {
            throw new CommandException("Invalid Arguments");
        }

        StringBuilder songList = new StringBuilder();

        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(args);

        try {
            Genre genre = parsedArgs.get("genre") != null ? Genre.fromName(parsedArgs.get("genre")) : null;

            // if the passed as arg year != null and is a number <= current year
            String year =
                    parsedArgs.get("year") != null &&
                            Integer.parseInt(parsedArgs.get("year")) <= Year.now().getValue()
                            ? parsedArgs.get("year") : null;

            Artist artist = parsedArgs.get("artist") != null ? artistActions.getArtistByUsername(parsedArgs.get("artist")) : null;

            List<Song> filteredSongs = songActions.filterSongs(artist, genre, year);

            for (Song song : filteredSongs) {
                songList.append(song.toString()).append("\n");
            }

            return songList.toString();
        } catch (NumberFormatException e) {
            throw new CommandException("An error occurred while filtering");
        }
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда списък с песни с възможност за филтриране.\n" +
                "   Usage: listsongs [artist=<artist>] [genre=<genre>] [year=<year>]";
    }
}
