package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class RemoveFromPlaylistCommand extends Command {
    private MusicPlaylists musicPlaylists;
    public RemoveFromPlaylistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за премахване на песен от плейлист
     * @param args аргументи които командата приема
     * @return Съобщение за успех
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 2);

        try {
            musicPlaylists.removeSongFromPlaylist(
                    musicPlaylists.getPlaylistActions().getPlaylistByName(args.get(0)),
                    musicPlaylists.getSongActions().getSongById(Integer.parseInt(args.get(1)))
            );
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
