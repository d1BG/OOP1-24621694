package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class ListPlaylistsCommand extends Command {
    private PlaylistActions playlistActions;
    public ListPlaylistsCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    @Override
    public String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args);

        StringBuilder listOfPlaylists = new StringBuilder();
        playlistActions.getPlaylists().forEach(playlist -> {
            listOfPlaylists.append(playlist.getName()).append("\t");
            listOfPlaylists.append(playlist.getDescription()).append("\n");
            for (Song song : playlist.getSongs()) {
                listOfPlaylists
                        .append(song)
                        .append("\n");
            }
        });
        return listOfPlaylists.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "ДЕБЪГ КОМАНДА Изкарва имената на всички плейлисти.\n" +
                "   Usage: listplaylists";
    }
}
