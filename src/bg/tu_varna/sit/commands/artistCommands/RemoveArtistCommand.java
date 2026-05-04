package bg.tu_varna.sit.commands.artistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда изтриваща артист и всички негови песни
 */
public class RemoveArtistCommand extends Command {
    /**
     * Мениджъра за музикални плейлисти
     */
    private MusicPlaylists musicPlaylists;

    /**
     * Конструктор на команда за премахване на артист и всички негови песни
     * @param musicPlaylists Мениджъра за музикални плейлисти
     */
    public RemoveArtistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за премахване на артист
     * @param args аргументи които командата приема
     * @return съобщение за успех с псевдонима на пемахнатия артист
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);

        Artist artist = musicPlaylists.getArtistActions().getArtistByUsername(args.getFirst());
        musicPlaylists.removeArtist(artist);
        return "Successfully removed artist: " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Премахва артист по псевдоним.\n" +
                "   Usage: removeartist <username>";
    }
}
