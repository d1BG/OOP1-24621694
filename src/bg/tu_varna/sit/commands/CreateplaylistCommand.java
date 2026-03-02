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
