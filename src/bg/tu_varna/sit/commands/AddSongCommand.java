package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Genre;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddSongCommand implements Command {
    private SongActions songActions;
    public AddSongCommand(SongActions songActions) {
        this.songActions = songActions;
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


        try {
            Genre genre = parsedArgs.get("genre") != null ? Genre.fromName(parsedArgs.get("genre")) : Genre.NA;

            // if the passed as arg year != null and is a number <= current year
            String year =
                    parsedArgs.get("year") != null &&
                            Integer.parseInt(parsedArgs.get("year")) <= Year.now().getValue()
                            ? parsedArgs.get("year") : null;

            String album = parsedArgs.get("album") != null ? parsedArgs.get("album") : null;

            songActions.addSong(new Song(songID, args.get(0), args.get(1), args.get(2), album, year, genre));
        } catch (NumberFormatException e) {
            throw new CommandException("Year must be a number");
        }
        return "Added new song with ID:" + songID;
    }

    @Override
    public String cmdHelpMessage() {
        return "Добавя нова песен. Ако съществува песен със същото <title> и <artist>, връща грешка.\n" +
                "   Usage: addsong <title> <artist> <duration> [alubm=<album>] [year=<year>] [genre=<genre>]";
    }
}
