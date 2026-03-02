package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.PlaylistActions;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Playlist;

import java.util.List;

public class CreateplaylistCommand implements Command {
    private PlaylistActions playlistActions;
    public CreateplaylistCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty() || args.size() > 2) {
            throw new PlaylistException("Invalid arguments");
        }

        if (args.size() == 1) {
            playlistActions.createPlaylist(args.get(0), "");
        } else { // 2
            playlistActions.createPlaylist(args.get(0), args.get(1));
        }

        return "Successfully added playlist " + args.get(0);
    }

    @Override
    public String cmdHelpMessage() {
        return "Създава нов плейлист с уникално име.\n" +
                "   Usage: createplaylist <name> [<description>]";
    }
}
