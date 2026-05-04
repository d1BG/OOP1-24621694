package bg.tu_varna.sit.commands.songCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда премахваща песен
 */
public class RemoveSongCommand extends Command {
    /**
     * Мениджър за песни
     */
    private SongActions songActions;

    /**
     * Конструктор за команда премахваща песен от мениджъра
     * @param songActions Мениджър за песни
     */
    public RemoveSongCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    /**
     * Изълняващ метод на команда за премахване на песен
     * @param args аргументи които командата приема
     * @return Съобщение съдържащо уникалния идентификатор на песента която е премахната при успех
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);

        try{
            songActions.removeSong(songActions.getSongById(Integer.parseInt(args.getFirst())));
        } catch (NumberFormatException e) {
            throw new CommandException("ID must be a number");
        }
        return "Successfully removed song with id " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Изтрива песен по ID.\n" +
                "   Usage: removesong <songId>";
    }
}
