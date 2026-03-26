package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;

import java.util.List;

public class AddToPlaylistCommand implements Command {
    private MusicPlaylists musicPlaylists;
    public AddToPlaylistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() < 2 || args.size() > 3) {
            throw new CommandException("Invalid arguments");
        }

        try {
            if (args.size() == 3) {
                musicPlaylists.addSongToPlaylist(args.get(0), Integer.parseInt(args.get(1)), Integer.parseInt(args.get(2)) - 1);
            } else {
                musicPlaylists.addSongToPlaylist(args.get(0), Integer.parseInt(args.get(1)), null);
            }
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid arguments");
        }

        return "Successfully added song to playlist";
    }

    @Override
    public String cmdHelpMessage() {
        return "Добавя песен към плейлист. Ако е зададена позиция – добавя я там.\n" +
                "   Usage: addtoplaylist <playlistName> <songId> [pos=<n>]";
    }
}
