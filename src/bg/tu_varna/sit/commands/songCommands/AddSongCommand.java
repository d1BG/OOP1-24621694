package bg.tu_varna.sit.commands.songCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.Genre;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddSongCommand implements Command {
    private SongActions songActions;
    private ArtistActions artistActions;

    public AddSongCommand(SongActions songActions, ArtistActions artistActions) {
        this.songActions = songActions;
        this.artistActions = artistActions;
    }

    @Override
    public String execute(List<String> args) {
        List<Song> songs = songActions.getSongs();
        if (args.size() < 3 || args.size() > 6) {
            throw new CommandException("Invalid arguments");
        }

        List<String> newArgs = new ArrayList<>();
        for (int i = 3; i < args.size(); i++){
            newArgs.add(args.get(i));
        }

        int songID;
        if (songs.isEmpty()) {
            songID = 1;
        } else {
            songID = songs.getLast().getID()+1;
        }

        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(newArgs);

        Artist artist = artistActions.getArtistByUsername(args.get(1));

        Genre genre = parsedArgs.get("genre") != null ? Genre.fromName(parsedArgs.get("genre")) : Genre.NA;

        String year;
        try {
            // if the passed as arg year != null and is a number <= current year
            year = parsedArgs.get("year") != null &&
                    Integer.parseInt(parsedArgs.get("year")) <= Year.now().getValue()
                    ? parsedArgs.get("year") : null;
        } catch (NumberFormatException e) {
            throw new CommandException("Year must be a number");
        }

        String album = parsedArgs.get("album") != null ? parsedArgs.get("album") : null;

        songActions.addSong(new Song(songID, args.get(0), artist , args.get(2), album, year, genre));

        return "Added new song with ID:" + songID;
    }

    @Override
    public String cmdHelpMessage() {
        return "Добавя нова песен. Ако съществува песен със същото <title> и <artist>, връща грешка.\n" +
                "   Usage: addsong <title> <artist> <duration> [alubm=<album>] [year=<year>] [genre=<genre>]";
    }
}
