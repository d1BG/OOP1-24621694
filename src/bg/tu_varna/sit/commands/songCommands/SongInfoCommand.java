package bg.tu_varna.sit.commands.songCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда намираща информация за песента
 */
public class SongInfoCommand extends Command {
    /**
     * Мениджър за песни
     */
    private SongActions songActions;

    /**
     * Конструктор за команда за информация на песен
     * @param songActions мениджъра на песни
     */
    public SongInfoCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    /**
     * Изълняващ метод на команда за извеждане на информация за песен
     * @param args аргументи които командата приема
     * @return подробна информация за песен
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);

        int id;
        try{
            id = Integer.parseInt(args.getFirst());
        } catch (NumberFormatException e) {
            throw new CommandException("ID must be a number");
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
