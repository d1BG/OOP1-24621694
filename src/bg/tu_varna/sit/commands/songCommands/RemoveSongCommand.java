package bg.tu_varna.sit.commands.songCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.SongException;

import java.util.List;

public class RemoveSongCommand implements Command {
    private SongActions songActions;
    public RemoveSongCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 1) {
            throw new SongException("Invalid arguments");
        }
        try{
            int id = Integer.parseInt(args.getFirst());
            songActions.removeSong(id);
            return "Successfully removed song with id " + id;
        } catch (NumberFormatException e) {
            throw new SongException("ID must be a number");
        }
    }

    @Override
    public String cmdHelpMessage() {
        return "Изтрива песен по ID.\n" +
                "   Usage: removesong <songId>";
    }
}
