package bg.tu_varna.sit.models;

import bg.tu_varna.sit.util.DateTimeParser;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlayHistoryEntry implements Serializable {
    private Song song;
    private LocalDateTime timestamp;
    private Playlist playlist; // may be null

    /**
     * Конструктор за обект съхраняваащ едно пускане на песен
     * Времето в което се пуска се взима от вмоменташното време на системата.
     * @param song Песента която бива пусната - задължително
     * @param playlist Плейлист от който бива пусната песента (може да е {@code null})
     */
    public PlayHistoryEntry(Song song, Playlist playlist) {
        this.song = song;
        this.timestamp = LocalDateTime.now();
        this.playlist = playlist;
    }

    /**
     * Конструктор за обект съхраняваащ едно пускане на песен
     * Използва се САМО за генериране на произполни данни.
     * @param song Песента която бива пусната - задължително
     * @param playlist Плейлист от който бива пусната песента (може да е {@code null})
     * @param timestamp Времето когато е пусната песен (подава се от произволния генератор)
     */
    public PlayHistoryEntry(Song song, Playlist playlist, LocalDateTime timestamp) {
        this.song = song;
        this.timestamp = timestamp;
        this.playlist = playlist;
    }

    /**
     * @return песента в това пускане
     */
    public Song getSong() {
        return song;
    }

    /**
     * @return времето на пускане
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @return {@code String} времето на пускане в четим формат.
     */
    public String getFancyTimestamp() {
        return DateTimeParser.format(timestamp);
    }

    /**
     * @return Плейлиста на това пускане. (Може да е {@code null})
     */
    public Playlist getPlaylist() {
        return playlist;
    }

    /**
     * @return лесно четимо описание на това пускане.
     */
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
