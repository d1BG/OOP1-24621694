package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylistsInterface;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public class RemovesongCommand implements Command {
    private MusicPlaylistsInterface musicPlaylists;
    public RemovesongCommand(MusicPlaylistsInterface musicPlaylists) {
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

    @Override
    public String cmdHelpMessage() {
        return "Изтрива песен по ID.\n" +
                "   Usage: removesong <songId>";
    }
}
