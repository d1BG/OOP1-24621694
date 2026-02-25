package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.MusicPlaylistsInterface;

import java.util.List;

public class ListplaylistsCommand implements Command {
    private MusicPlaylistsInterface musicPlaylists;
    public ListplaylistsCommand(MusicPlaylistsInterface musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public void execute(List<String> args) {
        musicPlaylists.getPlaylists().forEach(playlist -> {
            System.out.println(playlist.getName());
        });
    }

    @Override
    public String cmdHelpMessage() {
        return "ДЕБЪГ КОМАНДА Изкарва имената на всички плейлисти.\n" +
                "   Usage: listplaylists";
    }
}
