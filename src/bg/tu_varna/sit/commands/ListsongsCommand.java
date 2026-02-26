package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylists;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public class ListsongsCommand implements Command {
    private MusicPlaylists musicPlaylists;
    public ListsongsCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    // TODO: Filtering by artist, genre, year. This will do for now.
    @Override
    public String execute(List<String> args) {
        StringBuilder songList = new StringBuilder();
        List<Song> songs = musicPlaylists.getSongs();
        for (Song song : songs) {
            songList.append(song.toString()).append("\n");
        }
        return songList.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда списък с песни с възможност за филтриране.\n" +
                "   Usage: listsongs [artist=<artist>] [genre=<genre>] [year=<year>]";
    }
}
