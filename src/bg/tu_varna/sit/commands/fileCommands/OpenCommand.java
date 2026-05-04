package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.FileException;
import bg.tu_varna.sit.util.ArgumentParser;

import java.io.File;
import java.util.List;

/**
 * Клас за команда отвараща файл и зареждаща информация от него
 */
public class OpenCommand extends Command {
    /**
     * Мениджър за файлови операции
     */
    private FileActions fileActions;
    /**
     * Мениджъра за музикални плейлисти, който бива записан в файла
     */
    private MusicPlaylists musicPlaylists;

    /**
     * Конструктор на команда отвараща и зареждаща информация от подаден файл
     * @param musicPlaylists Мениджъра за музикални плейлисти
     * @param fileActions Мениджър за файлови операции
     */
    public OpenCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.fileActions = fileActions;
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за отваряне и зареждане на подаден файл
     * @param args аргументи които командата приема
     * @return Съобщение за успех с директорията на отворения файл
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);


        File file = new File(args.getFirst());
        fileActions.open(musicPlaylists, file);
        return "Successfully opened: " + file.getAbsolutePath();
    }

    @Override
    public String cmdHelpMessage() {
        return "Отваря файл със записани данни." +
                "   Usage: open <file path>";
    }
}
