package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.artistCommands.*;
import bg.tu_varna.sit.commands.fileCommands.*;
import bg.tu_varna.sit.commands.genericCommands.*;
import bg.tu_varna.sit.commands.playHistoryCommands.*;
import bg.tu_varna.sit.commands.playlistCommands.*;
import bg.tu_varna.sit.commands.songCommands.*;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commands {
    private final Map<String, Command> commands = new HashMap<>();

    public Commands(MusicPlaylists musicPlaylists, FileActions fileActions) {
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand(commands));
        commands.put("createplaylist", new CreatePlaylistCommand(musicPlaylists.getPlaylistActions()));
        commands.put("listplaylists", new ListPlaylistsCommand(musicPlaylists.getPlaylistActions()));
        commands.put("deleteplaylist", new DeletePlaylistCommand(musicPlaylists.getPlaylistActions()));
        commands.put("addsong", new AddSongCommand(musicPlaylists.getSongActions(), musicPlaylists.getArtistActions()));
        commands.put("listsongs", new ListSongsCommand(musicPlaylists.getSongActions(), musicPlaylists.getArtistActions()));
        commands.put("songinfo", new SongInfoCommand(musicPlaylists.getSongActions()));
        commands.put("removesong", new RemoveSongCommand(musicPlaylists.getSongActions()));
        commands.put("saveas", new SaveAsCommand(musicPlaylists, fileActions));
        commands.put("save", new SaveCommand(musicPlaylists, fileActions));
        commands.put("open", new OpenCommand(musicPlaylists, fileActions));
        commands.put("close", new CloseCommand(musicPlaylists, fileActions));
        commands.put("addtoplaylist", new AddToPlaylistCommand(musicPlaylists));
        commands.put("removefromplaylist", new RemoveFromPlaylist(musicPlaylists));
        commands.put("play", new PlayCommand(musicPlaylists));
        commands.put("plays", new PlaysCommand(musicPlaylists));
        commands.put("showplaylist", new ShowPlaylistCommand(musicPlaylists.getPlaylistActions()));
        commands.put("dropplaylist", new DropPlaylistCommand(musicPlaylists));
        commands.put("addartist", new AddArtistCommand(musicPlaylists.getArtistActions()));
        commands.put("listartists", new ListArtistsCommand(musicPlaylists.getArtistActions()));
        commands.put("removeartist", new RemoveArtistCommand(musicPlaylists));
    }

    public String exec(String cmdName, List<String> args) {
        Command cmd = commands.get(cmdName.toLowerCase());
        if (cmd != null) {
            return cmd.execute(args);
        } else {
            throw new CommandException("Unknown command: " + cmdName);
        }
    }
}