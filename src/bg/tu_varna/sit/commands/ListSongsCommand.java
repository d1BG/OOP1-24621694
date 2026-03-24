package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public class ListSongsCommand implements Command {
    private SongActions songActions;
    public ListSongsCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder songList = new StringBuilder();
        List<Song> songs = songActions.getSongs();
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
