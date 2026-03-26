package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.exceptions.PlaylistException;

import java.util.List;

public class DeletePlaylistCommand implements Command {
    private PlaylistActions playlistActions;
    public DeletePlaylistCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 1) {
            throw new PlaylistException("Invalid arguments");
        }
        playlistActions.deletePlaylist(args.getFirst());
        return "Successfully deleted playlist " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Изтрива плейлист.\n" +
                "   Usage: deleteplaylist <name>";
    }
}
