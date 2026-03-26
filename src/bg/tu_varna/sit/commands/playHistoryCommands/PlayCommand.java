package bg.tu_varna.sit.commands.playHistoryCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;
import java.util.Map;

public class PlayCommand implements Command {
    private MusicPlaylists musicPlaylists;
    public PlayCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty() || args.size() > 2) {
            throw new CommandException("Invalid Arguments");
        }

        try {
            Song song = musicPlaylists.getSongActions().getSong(Integer.parseInt(args.getFirst()));

            if (args.size() == 2) {
                Map<String, String> optPlaylist = ArgumentParser.KeyValueParser(args.get(1));
                Playlist playlist = musicPlaylists.getPlaylistActions().getPlaylistByName(optPlaylist.get("playlist"));

                if (!playlist.contains(song)) {
                    throw new PlaylistException("Song is not in the provided playlist");
                }

                musicPlaylists.getPlayHistoryActions().play(song, playlist);
                return "Played: " + song.toString() + " from playlist " + playlist.getName();
            }
            musicPlaylists.getPlayHistoryActions().play(song, null);
            return "Played: " + song.toString();
        } catch (NumberFormatException e) {
            throw new CommandException("Song ID must be a number");
        }
    }

    @Override
    public String cmdHelpMessage() {
        return "Отбелязва слушане на песен (записва дата и час).\n" +
                "   Usage: play <songId> [playlist=<playlistName>]";
    }
}
