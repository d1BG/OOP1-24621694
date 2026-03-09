package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.FileActions;
import bg.tu_varna.sit.data.MusicPlaylists;
import bg.tu_varna.sit.exceptions.FileException;

import java.util.List;

public class SaveCommand implements Command {
    private FileActions fileActions;
    private MusicPlaylists musicPlaylists;

    public SaveCommand(MusicPlaylists musicPlaylists, FileActions fileActions) {
        this.fileActions = fileActions;
        this.musicPlaylists = musicPlaylists;
    }
    @Override
    public String execute(List<String> args) {
        if (!args.isEmpty()) {
            throw new FileException("Invalid Arguments");
        }

        try {
            fileActions.write(musicPlaylists,null);
        } catch(FileException e) {
            throw new FileException(e.getMessage());
        }

        return "Saved to file: " + fileActions.getFile().getAbsolutePath();
    }

    @Override
    public String cmdHelpMessage() {
        return "Записва промените в текущия файл.\n" +
                "   Usage: save";
    }
}
