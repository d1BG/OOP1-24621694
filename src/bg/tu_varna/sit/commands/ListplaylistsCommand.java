package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylists;

import java.util.List;

public class ListplaylistsCommand implements Command {
    private MusicPlaylists musicPlaylists;
    public ListplaylistsCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder listOfPlaylists = new StringBuilder();
        musicPlaylists.getPlaylists().forEach(playlist -> {
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
