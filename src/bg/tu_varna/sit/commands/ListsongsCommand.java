package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylistsInterface;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public class ListsongsCommand implements Command {
    private MusicPlaylistsInterface musicPlaylists;
    public ListsongsCommand(MusicPlaylistsInterface musicPlaylists) {
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
