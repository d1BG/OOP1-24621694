package bg.tu_varna.sit;

import java.util.List;

public class ExitCommand implements Command{
    private MusicPlaylists musicPlaylists;
    public ExitCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public void execute(List<String> args) {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
