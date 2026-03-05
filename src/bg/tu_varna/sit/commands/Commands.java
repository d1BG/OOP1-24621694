package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commands {
    private final Map<String, Command> commands = new HashMap<>();

    public Commands(MusicPlaylists musicPlaylists) {
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand(commands));
        commands.put("createplaylist", new CreatePlaylistCommand(musicPlaylists.getPlaylistActions()));
        commands.put("listplaylists", new ListPlaylistsCommand(musicPlaylists.getPlaylistActions()));
        commands.put("deleteplaylist", new DeletePlaylistCommand(musicPlaylists.getPlaylistActions()));
        commands.put("addsong", new AddSongCommand(musicPlaylists.getSongManager()));
        commands.put("listsongs", new ListSongsCommand(musicPlaylists.getSongManager()));
        commands.put("songinfo", new SongInfoCommand(musicPlaylists.getSongManager()));
        commands.put("removesong", new RemoveSongCommand(musicPlaylists.getSongManager()));
        commands.put("saveas", new SaveAsCommand(musicPlaylists));
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