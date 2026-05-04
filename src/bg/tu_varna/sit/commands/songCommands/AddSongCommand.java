package bg.tu_varna.sit.commands.songCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.Genre;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.models.TimeDuration;
import bg.tu_varna.sit.util.ArgumentParser;
import bg.tu_varna.sit.util.DateTimeParser;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddSongCommand extends Command {
    /**
     * Мениджър за песни
     */
    private SongActions songActions;

    /**
     * Мениджър за артисти
     */
    private ArtistActions artistActions;

    /**
     * Конеструктор за команда добавяща песен в мениджъра
     * @param songActions Мениджър за песни
     * @param artistActions Мениджър за артисти
     */
    public AddSongCommand(SongActions songActions, ArtistActions artistActions) {
        this.songActions = songActions;
        this.artistActions = artistActions;
    }

    /**
     * Изълняващ метод на команда за добавяне на песен
     * @param args аргументи които командата приема
     * @return Съобщение за усепх с идентификатора на новодобавената песен
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 3, 6);

        List<Song> songs = songActions.getSongs();

        List<String> newArgs = new ArrayList<>();
        for (int i = 3; i < args.size(); i++){
            newArgs.add(args.get(i));
        }

        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(newArgs);

        TimeDuration duration = new TimeDuration(args.get(2));

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
        songActions.addSong(new Song(songActions.getNextSongID(), args.get(0), artist , duration, album, year, genre));

        return "Added new song with ID:" + (songActions.getNextSongID()-1);
    }

    @Override
    public String cmdHelpMessage() {
        return "Добавя нова песен. Ако съществува песен със същото <title> и <artist>, връща грешка.\n" +
                "   Usage: addsong <title> <artist> <duration> [alubm=<album>] [year=<year>] [genre=<genre>]";
    }
}
