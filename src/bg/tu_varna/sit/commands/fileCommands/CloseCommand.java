package bg.tu_varna.sit.commands.fileCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;

import java.util.List;

public class CloseCommand implements Command {
    private FileActions fileActions;
    private MusicPlaylists musicPlaylists;
    public CloseCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.fileActions = fileActions;
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args) {
        fileActions.close(musicPlaylists);
        return "";
    }

    @Override
    public String cmdHelpMessage() {
        return "Затваря текущия файл.\n" +
                "   Usage: close";
    }
}
