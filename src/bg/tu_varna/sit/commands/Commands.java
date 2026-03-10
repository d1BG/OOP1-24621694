package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.FileActions;
import bg.tu_varna.sit.data.MusicPlaylists;
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
        commands.put("addsong", new AddSongCommand(musicPlaylists.getSongActions()));
        commands.put("listsongs", new ListSongsCommand(musicPlaylists.getSongActions()));
        commands.put("songinfo", new SongInfoCommand(musicPlaylists.getSongActions()));
        commands.put("removesong", new RemoveSongCommand(musicPlaylists.getSongActions()));
        commands.put("saveas", new SaveAsCommand(musicPlaylists, fileActions));
        commands.put("save", new SaveCommand(musicPlaylists, fileActions));
        commands.put("open", new OpenCommand(musicPlaylists, fileActions));
        commands.put("close", new CloseCommand(musicPlaylists, fileActions));
        commands.put("addtoplaylist", new AddToPlaylistCommand(musicPlaylists));
        commands.put("removefromplaylist", new RemoveFromPlaylist(musicPlaylists));
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