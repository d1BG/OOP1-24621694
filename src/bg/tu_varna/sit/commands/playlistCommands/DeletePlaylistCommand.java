package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за изтриване на плейлист (НЕ изтрива пускания)
 */
public class DeletePlaylistCommand extends Command {
    /**
     * Мениджър за плейлисти
     */
    private PlaylistActions playlistActions;

    /**
     * Конструктор на команда за изтриване на плейлисти
     * @param playlistActions
     */
    public DeletePlaylistCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    /**
     * Изълняващ метод на команда за изтриване на плейлист
     * @param args аргументи които командата приема
     * @return съобщение за успех и името на изтрития плейлист
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);

        playlistActions.deletePlaylist(playlistActions.getPlaylistByName(args.getFirst()));
        return "Successfully deleted playlist " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Изтрива плейлист.\n" +
                "   Usage: deleteplaylist <name>";
    }
}
