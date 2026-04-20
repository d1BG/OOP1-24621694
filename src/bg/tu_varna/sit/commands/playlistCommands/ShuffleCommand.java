package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class ShuffleCommand extends Command {
    private PlaylistActions playlistActions;
    public ShuffleCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    /**
     * Изълняващ метод на команда за разбъркване на плейлист
     * @param args аргументи които командата приема
     * @return съобщение за успех и името на разбъркания плейлист
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1, 2);
        // if arg1 (seed=<n>) is not null -> parse it and grab the value with the 'seed' key and finally parse it to int
        Integer seed;
        try {
            seed = args.size() == 2 ?
                    Integer.parseInt(ArgumentParser.KeyValueParser(args.get(1)).get("seed")) : null;
        } catch (NumberFormatException e) {
            throw new CommandException("Seed must be a number");
        }
        playlistActions.shuffle(playlistActions.getPlaylistByName(args.getFirst()), seed);
        return "Shuffled playlist " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Разбърква песните в плейлист.\n" +
                "   Usage: shuffle <playlistName> [seed=<n>]";
    }
}
