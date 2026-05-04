package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.util.ArgumentParser;

import java.io.File;
import java.util.List;

/**
 * Клас за команда запасваща информация в файл
 */
public class SaveAsCommand extends Command {
    /**
     * Мениджър за файлови операции
     */
    private FileActions fileActions;
    /**
     * Мениджъра за музикални плейлисти, който бива записан в файла
     */
    private MusicPlaylists musicPlaylists;

    /**
     * Конструктор на команда запазваща информация в специфичвен файл
     * @param musicPlaylists Мениджъра за музикални плейлисти
     * @param fileActions Мениджър за файлови операции
     */
    public SaveAsCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.musicPlaylists = musicPlaylists;
        this.fileActions = fileActions;
    }

    /**
     * Изълняващ метод на команда за запазване на подаден от потребителя файл (и директория)
     * @param args аргументи които командата приема
     * @return съобщение за успех с директорията на отворения файл
     */
    @Override
    protected String execute(List<String> args){
        ArgumentParser.argSizeChecker(args, 1);


        File file = new File(args.getFirst());

        fileActions.write(musicPlaylists, file);
        return "Saved to file: " + file.getAbsolutePath();
    }

    @Override
    public String cmdHelpMessage() {
        return "Записва данните в нов файл.\n" +
                "   Usage: saveas <file path>";
    }
}
