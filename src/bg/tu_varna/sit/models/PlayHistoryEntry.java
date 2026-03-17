package bg.tu_varna.sit.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlayHistoryEntry implements Serializable {
    private Song song;
    private LocalDateTime timestamp;
    private Playlist playlist; // may be null

    public PlayHistoryEntry(Song song, Playlist playlist) {
        this.song = song;
        this.timestamp = LocalDateTime.now();
        this.playlist = playlist;
    }

    public PlayHistoryEntry(Song song) {
        this.song = song;
        this.timestamp = LocalDateTime.now();
        this.playlist = null;
    }

    public Song getSong() {
        return song;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void playlistDeleted(Playlist playlist) {
        this.playlist = null;
    }
}
