package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.PlaylistActions;

import java.util.List;

public class ListplaylistsCommand implements Command {
    private PlaylistActions playlistActions;
    public ListplaylistsCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder listOfPlaylists = new StringBuilder();
        playlistActions.getPlaylists().forEach(playlist -> {
            listOfPlaylists.append(playlist.getName()).append("\n");
        });
        return listOfPlaylists.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "ДЕБЪГ КОМАНДА Изкарва имената на всички плейлисти.\n" +
                "   Usage: listplaylists";
    }
}
