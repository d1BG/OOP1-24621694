package bg.tu_varna.sit;

import java.util.List;

public class DeleteplaylistCommand implements Command {
    MusicPlaylists musicPlaylists;
    public DeleteplaylistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public void execute(List<String> args) {
        if (args.isEmpty()) {
            System.out.println("Invalid arguments");
            return;
        }

        List<Playlist> playlists = musicPlaylists.getPlaylists();
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(args.getFirst())) {
                playlists.remove(playlist);
                return;
            }
        }
        System.out.println("Playlist " + args.getFirst() + " not found");

    }
}
