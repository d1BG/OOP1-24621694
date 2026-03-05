package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.FileActions;
import bg.tu_varna.sit.data.FileService;
import bg.tu_varna.sit.data.MusicPlaylists;
import bg.tu_varna.sit.exceptions.FileException;

import java.io.File;
import java.util.List;

public class SaveAsCommand implements Command {
    MusicPlaylists musicPlaylists;
    public SaveAsCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args){
        File file = new File(args.getFirst());
        // TODO: open FileActions somewhere else and pass it so other save commands will be able to use it
        FileActions fileActions = new FileService(file);
        if (!fileActions.write(musicPlaylists, null)) {
            throw new FileException("Could not save file");
        }
        return "Saved to file: " + file.getAbsolutePath();
    }

    @Override
    public String cmdHelpMessage() {
        return "";
    }
}
