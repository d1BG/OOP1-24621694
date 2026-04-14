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

    // Only used for the Generate Command
    public PlayHistoryEntry(Song song, Playlist playlist, LocalDateTime timestamp) {
        this.song = song;
        this.timestamp = timestamp;
        this.playlist = playlist;
    }

    public Song getSong() {
        return song;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getFancyTimestamp() {
        return timestamp.getDayOfMonth() + "." +
                timestamp.getMonthValue() + "." +
                timestamp.getYear() + "-" +
                timestamp.getHour() + ":" +
                timestamp.getMinute();
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void playlistDeleted(Playlist playlist) {
        this.playlist = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(song).append(" (Played on: ").append(getFancyTimestamp()).append(")");
        if (playlist != null) {
            sb.append(" from playlist: ").append(playlist.getName());
        }
        return sb.toString();
    }
}
