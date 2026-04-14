package bg.tu_varna.sit.commands.songCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class SongInfoCommand extends Command {
    private SongActions songActions;
    public SongInfoCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);

        int id;
        try{
            id = Integer.parseInt(args.getFirst());
        } catch (NumberFormatException e) {
            throw new SongException("ID must be a number");
        }

        Song song = songActions.getSongById(id);
        return song.songInfo();
    }

    @Override
    public String cmdHelpMessage() {
        return "Показва подробна информация за песен.\n" +
                "   Usage: songinfo <songId>";
    }
}
