package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylists;
import bg.tu_varna.sit.data.PlaylistActions;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Playlist;

import java.util.List;

public class DeleteplaylistCommand implements Command {
    private PlaylistActions playlistActions;
    public DeleteplaylistCommand(PlaylistActions playlistActions) {
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
                playlists.remove(playlist);
                return "Successfully removed playlist " + playlist.getName();
            }
        }
        throw new PlaylistException("Playlist " + args.getFirst() + " not found");
    }

    @Override
    public String cmdHelpMessage() {
        return "Изтрива плейлист.\n" +
                "   Usage: deleteplaylist <name>";
    }
}
