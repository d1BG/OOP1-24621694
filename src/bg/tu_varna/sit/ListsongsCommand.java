package bg.tu_varna.sit;

import java.util.List;

public class ListsongsCommand implements Command {
    MusicPlaylists musicPlaylists;
    public ListsongsCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    // TODO: Filtering by artist, genre, year. This will do for now.
    @Override
    public void execute(List<String> args) {
        List<Song> songs = musicPlaylists.getSongs();
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}
