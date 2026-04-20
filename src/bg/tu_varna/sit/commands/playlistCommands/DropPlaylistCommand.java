package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class DropPlaylistCommand extends Command {
    private MusicPlaylists musicPlaylists;

    public DropPlaylistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за изтриване на плейлист и всички пускания в историята обвързани с плейлиста
     * @param args аргументи които командата приема
     * @return Съобщение за успех
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);


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
