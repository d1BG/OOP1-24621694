package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда премесваща песен от Н позиция в К позиция из плейлист
 */
public class MoveCommand extends Command {
    /**
     * Мениджър за плейлисти
     */
    private PlaylistActions playlistActions;

    /**
     * Конструктор за команда
     * @param playlistActions
     */
    public MoveCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    @Override
    protected String execute(List<String> args) throws CommandException {
        ArgumentParser.argSizeChecker(args, 3);

        Playlist playlist = playlistActions.getPlaylistByName(args.getFirst());

        int fromPos, toPos;
        try {
            fromPos = Integer.parseInt(args.get(1))-1;
            toPos = Integer.parseInt(args.get(2))-1;
        } catch (NumberFormatException e) {
            throw new CommandException("fromPos and toPos must be numbers");
        }

        if (fromPos < 0 || fromPos > playlist.getSongs().size() || toPos < 0 || toPos > playlist.getSongs().size()) {
            throw new CommandException("You can only move songs between 1 and " + playlist.getSongs().size() + " position");
        }

        playlistActions.move(playlist, fromPos, toPos);
        return "Successfully moved song from position " + (fromPos+1) + " to position " + (toPos+1) + " in playlist: " + playlist.getName();
    }

    @Override
    public String cmdHelpMessage() {
        return "Премества песен в рамките на плейлист.\n" +
                "   Usage: move <playlistName> <fromPos> <toPos>";
    }
}
