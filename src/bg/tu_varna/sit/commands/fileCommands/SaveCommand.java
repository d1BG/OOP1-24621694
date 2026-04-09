package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class SaveCommand extends Command {
    private FileActions fileActions;
    private MusicPlaylists musicPlaylists;

    public SaveCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.fileActions = fileActions;
        this.musicPlaylists = musicPlaylists;
    }
    @Override
    public String execute(List<String> args) {
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
