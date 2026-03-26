package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.exceptions.FileException;

import java.io.File;
import java.util.List;

public class OpenCommand implements Command {
    private FileActions fileActions;
    private MusicPlaylists musicPlaylists;
    public OpenCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.fileActions = fileActions;
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 1) {
            throw new CommandException("Invalid Arguments");
        }

        File file = new File(args.getFirst());
        if (fileActions.open(musicPlaylists, file)) {
            return "Successfully opened file: " + file.getAbsolutePath();
        }
        throw new FileException("Failed to open file: " + file.getAbsolutePath());
    }

    @Override
    public String cmdHelpMessage() {
        return "Отваря файл със записани данни." +
                "   Usage: open <file path>";
    }
}
