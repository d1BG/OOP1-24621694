package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.util.ArgumentParser;

import java.io.File;
import java.util.List;

public class SaveAsCommand implements Command {
    private MusicPlaylists musicPlaylists;
    private FileActions fileActions;
    public SaveAsCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.musicPlaylists = musicPlaylists;
        this.fileActions = fileActions;
    }

    @Override
    public String execute(List<String> args){
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
