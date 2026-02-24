package bg.tu_varna.sit;

import java.util.List;

public class ListsongsCommand implements Command {
    private MusicPlaylists musicPlaylists;
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

    @Override
    public String cmdHelpMessage() {
        return "Извежда списък с песни с възможност за филтриране.\n" +
                "   Usage: listsongs [artist=<artist>] [genre=<genre>] [year=<year>]";
    }
}
