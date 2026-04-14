package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class CloseCommand extends Command {
    private FileActions fileActions;
    private MusicPlaylists musicPlaylists;
    public CloseCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.fileActions = fileActions;
        this.musicPlaylists = musicPlaylists;
    }

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
