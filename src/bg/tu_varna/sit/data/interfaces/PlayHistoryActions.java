package bg.tu_varna.sit.data.interfaces;

import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PlayHistoryActions {
    List<PlayHistoryEntry> getEntries();
    void play(Song song, Playlist playlist);
    List<PlayHistoryEntry> filterEntries(LocalDateTime from, LocalDateTime to, Playlist playlist, Song song);
    String topPlaylists(int n, LocalDateTime from, LocalDateTime to);
    Map<Song, Integer> topTracks(int n, LocalDateTime from, LocalDateTime to);
    Map<Artist, Integer> topArtists(int n, LocalDateTime from, LocalDateTime to);
    String lowActivity(LocalDateTime from, LocalDateTime to, int percentThreshold);
}
