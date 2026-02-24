package bg.tu_varna.sit;

import java.util.List;

public class ListplaylistsCommand implements Command {
    MusicPlaylists musicPlaylists;
    public ListplaylistsCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public void execute(List<String> args) {
        musicPlaylists.getPlaylists().forEach(playlist -> {
            System.out.println(playlist.getName());
        });
    }
}
