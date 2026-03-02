package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.SongActions;
import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public class RemovesongCommand implements Command {
    private SongActions songActions;
    public RemovesongCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            throw new SongException("Invalid arguments");
        }
        List<Song> songs = songActions.getSongs();
        for (Song song : songs) {
            try {
                if (song.getID() == Integer.parseInt(args.getFirst())) {
                    songs.remove(song);
                    return "Successfully removed song " + song.getID();
                }
            } catch (NumberFormatException e) {
                throw new SongException("Please input a number");
            }
        }
        throw new SongException("Song " + args.getFirst() + " not found");
    }

    @Override
    public String cmdHelpMessage() {
        return "Изтрива песен по ID.\n" +
                "   Usage: removesong <songId>";
    }
}
