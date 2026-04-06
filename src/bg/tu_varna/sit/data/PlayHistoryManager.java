package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

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
    public List<PlayHistoryEntry> filterEntries(LocalDateTime from, LocalDateTime to, Playlist playlist, Song song) {
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

    // TODO: DUPLICATE CODE - condense into a single method
    @Override
    public Map<Playlist, Integer> topPlaylists(int n, LocalDateTime from, LocalDateTime to) {
        List<PlayHistoryEntry> filteredList = new ArrayList<>(entries);
        filteredList.removeIf(e-> e.getPlaylist() == null);

        if (from != null) {
            filteredList.removeIf(e -> e.getTimestamp().isBefore(from));
        }

        if (to != null) {
            filteredList.removeIf(e -> e.getTimestamp().isAfter(to));
        }

        Map<Playlist, Integer> counts = new HashMap<>();
        for (PlayHistoryEntry e : filteredList) {
            Playlist curr = e.getPlaylist();
            if (counts.get(curr) == null) {
                counts.put(curr, 1);
            } else {
                counts.replace(curr, counts.get(curr)+1);
            }
        }

        List<Map.Entry<Playlist, Integer>> entryList = new ArrayList<>(counts.entrySet());
        // compare and sort in descending order
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        Map<Playlist, Integer> topPlaylists = new HashMap<>();
        for (int i = 0; i < Math.min(n, entryList.size()); i++) {
            topPlaylists.put(entryList.get(i).getKey(), entryList.get(i).getValue());
        }
        return topPlaylists;
    }

    @Override
    public Map<Song, Integer> topTracks(int n, LocalDateTime from, LocalDateTime to) {
        List<PlayHistoryEntry> filteredList = new ArrayList<>(entries);
        if (from != null) {
            filteredList.removeIf(e -> e.getTimestamp().isBefore(from));
        }

        if (to != null) {
            filteredList.removeIf(e -> e.getTimestamp().isAfter(to));
        }

        Map<Song, Integer> counts = new HashMap<>();
        for (PlayHistoryEntry e : filteredList) {
            Song curr = e.getSong();
            if (counts.get(curr) == null) {
                counts.put(curr, 1);
            } else {
                counts.replace(curr, counts.get(curr)+1);
            }
        }

        List<Map.Entry<Song, Integer>> entryList = new ArrayList<>(counts.entrySet());
        // compare and sort in descending order
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        Map<Song, Integer> topTracks = new HashMap<>();
        for (int i = 0; i < Math.min(n, entryList.size()); i++) {
            topTracks.put(entryList.get(i).getKey(), entryList.get(i).getValue());
        }
        return topTracks;
    }

    @Override
    public Map<Artist, Integer> topArtists(int n, LocalDateTime from, LocalDateTime to) {
        List<PlayHistoryEntry> filteredList = new ArrayList<>(entries);
        if (from != null) {
            filteredList.removeIf(e -> e.getTimestamp().isBefore(from));
        }

        if (to != null) {
            filteredList.removeIf(e -> e.getTimestamp().isAfter(to));
        }

        Map<Artist, Integer> counts = new HashMap<>();
        for (PlayHistoryEntry e : filteredList) {
            Artist curr = e.getSong().getArtist();
            if (counts.get(curr) == null) {
                counts.put(curr, 1);
            } else {
                counts.replace(curr, counts.get(curr)+1);
            }
        }

        List<Map.Entry<Artist, Integer>> entryList = new ArrayList<>(counts.entrySet());
        // compare and sort in descending order
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        Map<Artist, Integer> topArtists = new HashMap<>();
        for (int i = 0; i < Math.min(n, entryList.size()); i++) {
            topArtists.put(entryList.get(i).getKey(), entryList.get(i).getValue());
        }
        return topArtists;
    }

    @Override
    public String lowActivity(LocalDateTime from, LocalDateTime to, int percentThreshold) {
        return "";
    }
}
