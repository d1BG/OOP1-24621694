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

        Integer pos = null;

        try {
            if (args.size() == 3) {
                pos = Integer.parseInt(args.get(2)) - 1;
            }
        } catch (NumberFormatException e) {
            throw new CommandException("Position must be a number");
        }

        musicPlaylists.addSongToPlaylist(
                musicPlaylists.getPlaylistActions().getPlaylistByName(args.get(0)),
                musicPlaylists.getSongActions().getSongById(Integer.parseInt(args.get(1))),
                pos
        );


        return "Successfully added song to playlist";
    }

    @Override
    public String cmdHelpMessage() {
        return "Добавя песен към плейлист. Ако е зададена позиция – добавя я там.\n" +
                "   Usage: addtoplaylist <playlistName> <songId> [pos=<n>]";
    }
}
