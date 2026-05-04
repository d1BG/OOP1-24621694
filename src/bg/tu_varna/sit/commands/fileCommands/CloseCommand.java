package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за затавяне на файл, и изчистване на информацията в програмата
 */
public class CloseCommand extends Command {
    /**
     * Мениджър за файлови операции
     */
    private FileActions fileActions;
    /**
     * Мениджъра за музикални плейлисти, от който се изтрива информацията в програмата
     */
    private MusicPlaylists musicPlaylists;

    /**
     * Конструктор на команда за затваряна файл и изтриване на инфомрацията в програмата
     * @param musicPlaylists
     * @param fileActions
     */
    public CloseCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.fileActions = fileActions;
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за затваряне и изчистване на заредената информация
     * @param args аргументи които командата приема
     * @return Съобщение аз успех
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args);

        fileActions.close(musicPlaylists);
        return "Successfully closed file";
    }

    @Override
    public String cmdHelpMessage() {
        return "Затваря текущия файл.\n" +
                "   Usage: close";
    }
}
