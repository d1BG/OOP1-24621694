package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;

import java.util.List;

public class RemoveFromPlaylist implements Command {
    private MusicPlaylists musicPlaylists;
    public RemoveFromPlaylist(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 2) {
            throw new CommandException("Invalid arguments");
        }

        try {
            musicPlaylists.removeSongFromPlaylist(args.get(0), Integer.parseInt(args.get(1)));
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid arguments");
        }

        return "Successfully removed the song from playlist";
    }

    @Override
    public String cmdHelpMessage() {
        return "Премахва песен от плейлист." +
                "    Usage: removefromplaylist <playlistName> <songId>";
    }
}
