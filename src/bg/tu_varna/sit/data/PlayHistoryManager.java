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
    List<PlayHistoryEntry> entries;

    public PlayHistoryManager() {
        this.entries = new ArrayList<>();
    }

    public List<PlayHistoryEntry> getEntries() {
        return entries;
    }

    public void addEntry(PlayHistoryEntry entry) {
        entries.add(entry);
    }



    // NOT IMPLEMENTED
    @Override
    public void play(Song song, Playlist playlist) {
        entries.add(new PlayHistoryEntry(song, playlist));
    }

    @Override
    public String plays(LocalDateTime from, LocalDateTime to, Playlist playlist, Song song) {
        return "";
    }

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
