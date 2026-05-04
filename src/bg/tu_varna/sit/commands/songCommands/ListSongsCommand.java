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
import java.util.List;
import java.util.Map;

public class ListSongsCommand extends Command {
    /**
     * Мениджър на песни
     */
    private SongActions songActions;
    /**
     * Мениджър на артисти
     */
    private ArtistActions artistActions;

    /**
     * Конструктор на команда за извеждане на всички песни
     * @param songActions Мениджър за песни
     * @param artistActions Мениджър за артисти
     */
    public ListSongsCommand(SongActions songActions, ArtistActions artistActions) {
        this.songActions = songActions;
        this.artistActions = artistActions;
    }

    /**
     * Изълняващ метод на команда за извеждане на всички песни
     * @param args аргументи които командата приема
     * @return символен низ от всички песни и кратка информация за тях (идентификатор, име, артист и продължителност)
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 0, 3);


        StringBuilder songList = new StringBuilder();

        Map<String, String> parsedArgs = ArgumentParser.KeyValueParser(args);

        Genre genre = parsedArgs.get("genre") != null ? Genre.fromName(parsedArgs.get("genre")) : null;

        String year;
        try {
            // if the passed as arg year != null and is a number <= current year
            year = parsedArgs.get("year") != null &&
                    Integer.parseInt(parsedArgs.get("year")) <= Year.now().getValue()
                    ? parsedArgs.get("year") : null;
        } catch (NumberFormatException e) {
            throw new CommandException("Year must be a number");
        }
        Artist artist = parsedArgs.get("artist") != null ? artistActions.getArtistByUsername(parsedArgs.get("artist")) : null;

        List<Song> filteredSongs = songActions.filterSongs(artist, genre, year);

        for (Song song : filteredSongs) {
            songList.append(song.toString()).append("\n");
        }

        return songList.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда списък с песни с възможност за филтриране.\n" +
                "   Usage: listsongs [artist=<artist>] [genre=<genre>] [year=<year>]";
    }
}
