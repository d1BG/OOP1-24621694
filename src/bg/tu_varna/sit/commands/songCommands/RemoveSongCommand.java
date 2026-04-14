package bg.tu_varna.sit.commands.songCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class RemoveSongCommand extends Command {
    private SongActions songActions;
    public RemoveSongCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);

        try{
            songActions.removeSong(songActions.getSongById(Integer.parseInt(args.getFirst())));
        } catch (NumberFormatException e) {
            throw new SongException("ID must be a number");
        }
        return "Successfully removed song with id " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Изтрива песен по ID.\n" +
                "   Usage: removesong <songId>";
    }
}
