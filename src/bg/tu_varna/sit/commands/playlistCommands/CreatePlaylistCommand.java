package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда създаваща плейлисти
 */
public class CreatePlaylistCommand extends Command {
    /**
     * Мениджър за плейлисти
     */
    private PlaylistActions playlistActions;

    /**
     * Конструктор за команда създаваща плейлисти
     * @param playlistActions Мениджър за плейлисти
     */
    public CreatePlaylistCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    /**
     * Изълняващ метод на команда за създааване на плейлист
     * @param args аргументи които командата приема
     * @return съобщение за успех с името на новосъздадения плейлист
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1, 2);

        Playlist playlist = new Playlist(args.get(0));

        if (args.size() == 2) {
            playlist.setDescription(args.get(1));
        }

        playlistActions.createPlaylist(playlist);
        return "Successfully added playlist " + args.get(0);
    }

    @Override
    public String cmdHelpMessage() {
        return "Създава нов плейлист с уникално име.\n" +
                "   Usage: createplaylist <name> [<description>]";
    }
}
