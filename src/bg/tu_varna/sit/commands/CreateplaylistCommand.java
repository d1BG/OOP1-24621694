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
        if (args.isEmpty()) {
            throw new PlaylistException("Invalid arguments");
        }

        List<Playlist> playlists = playlistActions.getPlaylists();
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(args.getFirst())) {
                throw new PlaylistException(playlist.getName() + " already exists");
            }
        }
        Playlist newPlaylist = new Playlist(args.getFirst());
        if (args.size() == 2) {
            newPlaylist.setDescription(args.get(1));
        }

        playlists.add(newPlaylist);
        return "Successfully added playlist " + newPlaylist.getName();
    }

    @Override
    public String cmdHelpMessage() {
        return "Създава нов плейлист с уникално име.\n" +
                "   Usage: createplaylist <name> [<description>]";
    }
}
