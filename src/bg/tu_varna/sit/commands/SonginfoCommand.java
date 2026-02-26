package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylistsInterface;
import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public class SonginfoCommand implements Command {
    private MusicPlaylistsInterface musicPlaylists;
    public SonginfoCommand(MusicPlaylistsInterface musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public void execute(List<String> args) {
        if (args.isEmpty()) {
            throw new SongException("Invalid arguments");
        }
        List<Song> songs = musicPlaylists.getSongs();
        for (Song song : songs) {
            try {
                if (song.getID() == Integer.parseInt(args.getFirst())) {
                    System.out.println(song);
                    return;
                }
            } catch (NumberFormatException e) {
                throw new SongException("Please input a number");
            }
        }
        throw new SongException("Song " + args.getFirst() + " not found");
    }

    @Override
    public String cmdHelpMessage() {
        return "Показва подробна информация за песен.\n" +
                "   Usage: songinfo <songId>";
    }
}
