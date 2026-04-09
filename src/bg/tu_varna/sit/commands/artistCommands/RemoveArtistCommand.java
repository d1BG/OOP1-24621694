package bg.tu_varna.sit.commands.artistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class RemoveArtistCommand implements Command {
    private MusicPlaylists musicPlaylists;
    public RemoveArtistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }
    @Override
    public String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);

        Artist artist = musicPlaylists.getArtistActions().getArtistByUsername(args.getFirst());
        musicPlaylists.removeArtist(artist);
        return "Successfully removed artist: " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Премахва артист по псевдоним.\n" +
                "   Usage: removeartist <username>";
    }
}
