package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlayHistoryManager implements Serializable, PlayHistoryActions {
    private List<PlayHistoryEntry> entries;

    public PlayHistoryManager() {
        this.entries = new ArrayList<>();
    }

    public List<PlayHistoryEntry> getEntries() {
        return entries;
    }

    @Override
    public void play(Song song, Playlist playlist) {
        entries.add(new PlayHistoryEntry(song, playlist));
    }

    @Override
    public List<PlayHistoryEntry> plays(LocalDateTime from, LocalDateTime to, Playlist playlist, Song song) {
        List<PlayHistoryEntry> filteredList = new ArrayList<>(entries);
        if (song != null) {
            filteredList.removeIf(e -> !e.getSong() .equals(song));
        }

        // extra check here bcuz Playlists can be null
        if (playlist != null) {
            filteredList.removeIf(e -> e.getPlaylist() != null && e.getPlaylist().equals(playlist));
        }

        if (from != null) {
            filteredList.removeIf(e -> e.getTimestamp().isBefore(from));
        }

        if (to != null) {
            filteredList.removeIf(e -> e.getTimestamp().isAfter(to));
        }

        return filteredList;
    }

    // NOT IMPLEMENTED
    @Override
    public String topPlaylists(int n, LocalDateTime from, LocalDateTime to) {
        return "";
    }

    @Override
    public String topTracks(int n, LocalDateTime from, LocalDateTime to) {
        return "";
    }

    @Override
    public String topArtists(int n, LocalDateTime from, LocalDateTime to) {
        return "";
    }

    @Override
    public String lowActivity(LocalDateTime from, LocalDateTime to, int percentThreshold) {
        return "";
    }
}
