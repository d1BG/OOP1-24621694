package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда запасваща информация в файл
 */
public class SaveCommand extends Command {
    /**
     * Мениджър за файлови операции
     */
    private FileActions fileActions;
    /**
     * Мениджъра за музикални плейлисти, който бива записан в файла
     */
    private MusicPlaylists musicPlaylists;

    /**
     * Конструктор на команда запасваща информация в файл
     * @param musicPlaylists
     * @param fileActions
     */
    public SaveCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.fileActions = fileActions;
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за запазване на отворения файл
     * @param args аргументи които командата приема
     * @return съобщение за успех с директорията на отворения файл
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args);


        fileActions.write(musicPlaylists,null);

        return "Saved to file: " + fileActions.getFile().getAbsolutePath();
    }

    @Override
    public String cmdHelpMessage() {
        return "Записва промените в текущия файл.\n" +
                "   Usage: save";
    }
}
