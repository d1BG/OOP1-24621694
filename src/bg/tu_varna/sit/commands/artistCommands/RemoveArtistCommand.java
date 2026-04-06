package bg.tu_varna.sit.commands.artistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Artist;

import java.util.List;

public class RemoveArtistCommand implements Command {
    MusicPlaylists musicPlaylists;
    public RemoveArtistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }
    @Override
    public String execute(List<String> args) {
        if (args.size() > 1) {
            throw new CommandException("Invalid Arguments");
        }
        Artist artist = musicPlaylists.getArtistActions().getArtistByUsername(args.getFirst());
        musicPlaylists.removeArtistByUsername(artist);
        return "Successfully removed artist: " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Премахва артист по псевдоним.\n" +
                "   Usage: removeartist <username>";
    }
}
