package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylistsInterface;
import bg.tu_varna.sit.exceptions.CommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commands {
    private final Map<String, Command> commands = new HashMap<>();

    public Commands(MusicPlaylistsInterface musicPlaylists) {
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand(commands));
        commands.put("createplaylist", new CreateplaylistCommand(musicPlaylists));
        commands.put("listplaylists", new ListplaylistsCommand(musicPlaylists));
        commands.put("deleteplaylist", new DeleteplaylistCommand(musicPlaylists));
        commands.put("addsong", new AddsongCommand(musicPlaylists));
        commands.put("listsongs", new ListsongsCommand(musicPlaylists));
        commands.put("songinfo", new SonginfoCommand(musicPlaylists));
        commands.put("removesong", new RemovesongCommand(musicPlaylists));
    }

    public void exec(String cmdName, List<String> args) {
        Command cmd = commands.get(cmdName.toLowerCase());
        if (cmd != null) {
            args.removeFirst();
            cmd.execute(args);
        } else {
            throw new CommandException("Unknown command: " + cmdName);
        }
    }
}