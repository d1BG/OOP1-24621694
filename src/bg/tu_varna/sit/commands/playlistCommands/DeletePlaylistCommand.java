package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class DeletePlaylistCommand implements Command {
    private PlaylistActions playlistActions;
    public DeletePlaylistCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    @Override
    public String execute(List<String> args) {
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
