package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylistsInterface;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Playlist;

import java.util.List;

public class CreateplaylistCommand implements Command {
    private MusicPlaylistsInterface musicPlaylists;
    public CreateplaylistCommand(MusicPlaylistsInterface musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public void execute(List<String> args) {
        List<Playlist> playlists = musicPlaylists.getPlaylists();
        if (!args.isEmpty()) {
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
        }
    }

    @Override
    public String cmdHelpMessage() {
        return "Създава нов плейлист с уникално име.\n" +
                "   Usage: createplaylist <name> [<description>]";
    }
}
