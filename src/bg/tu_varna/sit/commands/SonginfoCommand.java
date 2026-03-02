package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.SongActions;
import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public class SonginfoCommand implements Command {
    private SongActions songActions;
    public SonginfoCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 1) {
            throw new SongException("Invalid arguments");
        }

        try{
            int id = Integer.parseInt(args.getFirst());
            Song song = songActions.getSong(id);
            return song.toString();
        } catch (NumberFormatException e) {
            throw new SongException("ID must be a number");
        }
    }

    @Override
    public String cmdHelpMessage() {
        return "Показва подробна информация за песен.\n" +
                "   Usage: songinfo <songId>";
    }
}
