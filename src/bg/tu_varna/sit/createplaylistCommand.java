package bg.tu_varna.sit;

import java.util.List;

public class createplaylistCommand implements Command {
    private MusicPlaylists musicPlaylists;
    public createplaylistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public void execute(List<String> args) {
        List<Playlist> playlists = musicPlaylists.getPlaylists();
        if (!args.isEmpty()) {
            for (Playlist playlist : playlists) {
                if (playlist.getName().equals(args.getFirst())) {
                    System.out.println(playlist.getName() + " already exists");
                    return;
                }
            }
            Playlist newPlaylist = new Playlist(args.getFirst());
            if (args.size() == 2) {
                newPlaylist.setDescription(args.get(1));
            }

            playlists.add(newPlaylist);
            musicPlaylists.setPlaylists(playlists);
        }
    }
}
