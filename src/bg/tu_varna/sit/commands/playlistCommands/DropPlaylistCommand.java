package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Playlist;

import java.util.List;

public class DropPlaylistCommand implements Command {
    private MusicPlaylists musicPlaylists;

    public DropPlaylistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 1) {
            throw new CommandException("Invalid Arguments");
        }

        Playlist p = musicPlaylists.getPlaylistActions().getPlaylistByName(args.getFirst());
        musicPlaylists.dropPlaylist(p);

        return "Successfully dropped playlist";
    }

    @Override
    public String cmdHelpMessage() {
        return "Премахва плейлист от системата." +
                "   Usage: dropplaylist <playlistName>";
    }
}
