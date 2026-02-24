package bg.tu_varna.sit;

import java.util.List;

public class RemovesongCommand implements Command {
    private MusicPlaylists musicPlaylists;
    public RemovesongCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public void execute(List<String> args) {
        if (args.isEmpty()) {
            System.out.println("Invalid arguments");
            return;
        }
        List<Song> songs = musicPlaylists.getSongs();
        for (Song song : songs) {
            try {
                if (song.getID() == Integer.parseInt(args.getFirst())) {
                    songs.remove(song);
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a number");
                return;
            }
        }
        System.out.println("Song " + args.getFirst() + " not found");
    }
}
