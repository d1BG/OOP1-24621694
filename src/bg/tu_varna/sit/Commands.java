package bg.tu_varna.sit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commands {
    private final Map<String, Command> commands = new HashMap<>();

    public Commands(MusicPlaylists musicPlaylists) {
        commands.put("exit", new ExitCommand(musicPlaylists));
        commands.put("help", new HelpCommand(musicPlaylists));
        commands.put("createplaylist", new CreateplaylistCommand(musicPlaylists));
    }

    public void exec(String cmdName, List<String> args) {
        Command cmd = commands.get(cmdName.toLowerCase());
        if (cmd != null) {
            args.removeFirst();
            cmd.execute(args);
        } else {
            System.out.println("Unknown command: " + cmdName);
        }
    }
}