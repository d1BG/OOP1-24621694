package bg.tu_varna.sit.commands.playHistoryCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;
import java.util.Map;

/**
 * Клас за команда за пускане на песен
 */
public class PlayCommand extends Command {
    /**
     * мениджър на музикални плейлисти
     */
    private MusicPlaylists musicPlaylists;

    /**
     * Конструктор на команда за пускане на песен
     * @param musicPlaylists
     */
    public PlayCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за маркираща песен като слушана в историята
     * @param args аргументи които командата приема
     * @return съобщение за успех с името и плейлиста (ако е бил подаден) на песента
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1, 2);

        Song song;
        try {
            song = musicPlaylists.getSongActions().getSongById(Integer.parseInt(args.getFirst()));
        } catch (NumberFormatException e) {
            throw new CommandException("Song ID must be a number");
        }

        Playlist playlist = null;
        if (args.size() == 2) {
            Map<String, String> optPlaylist = ArgumentParser.KeyValueParser(args.get(1));
            playlist = musicPlaylists.getPlaylistActions().getPlaylistByName(optPlaylist.get("playlist"));
        }

        musicPlaylists.getPlayHistoryActions().play(song, playlist);
        StringBuilder sb = new StringBuilder("Played: ").append(song.toString());
        if (playlist != null) {
            sb.append(" from playlist").append(playlist.getName());
        }

        return sb.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Отбелязва слушане на песен (записва дата и час).\n" +
                "   Usage: play <songId> [playlist=<playlistName>]";
    }
}
