package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class MoveCommand extends Command {
    private PlaylistActions playlistActions;
    public MoveCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    @Override
    protected String execute(List<String> args) throws CommandException {
        ArgumentParser.argSizeChecker(args, 3);

        Playlist playlist = playlistActions.getPlaylistByName(args.getFirst());

        int fromPos, toPos;
        try {
            fromPos = Integer.parseInt(args.get(1));
            toPos = Integer.parseInt(args.get(2));
        } catch (NumberFormatException e) {
            throw new CommandException("fromPos and toPos must be numbers");
        }

        playlistActions.move(playlist, fromPos, toPos);
        return "Successfully moved song from position " + fromPos + " to position " + toPos + " in playlist: " + playlist.getName();
    }

    @Override
    public String cmdHelpMessage() {
        return "Премества песен в рамките на плейлист.\n" +
                "   Usage: move <playlistName> <fromPos> <toPos>";
    }
}
