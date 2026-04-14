package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    // used by random generator
    public void play(Song song, Playlist playlist, LocalDateTime timestamp) {
        entries.add(new PlayHistoryEntry(song, playlist, timestamp));
    }

    @Override
    public List<PlayHistoryEntry> filterEntries(LocalDateTime from, LocalDateTime to, Playlist playlist, Song song) {
        List<PlayHistoryEntry> filteredList = new ArrayList<>(entries);
        if (song != null) {
            filteredList.removeIf(e -> !e.getSong().equals(song));
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

    @Override
    public Map<Playlist, Integer> topPlaylists(int n, LocalDateTime from, LocalDateTime to) {
        List<PlayHistoryEntry> filteredList = filterEntries(from, to, null, null);
        filteredList.removeIf(e-> e.getPlaylist() == null);

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
        Map<Playlist, Integer> topPlaylists = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(n, entryList.size()); i++) {
            topPlaylists.put(entryList.get(i).getKey(), entryList.get(i).getValue());
        }
        return topPlaylists;
    }

    @Override
    public Map<Song, Integer> topTracks(int n, LocalDateTime from, LocalDateTime to) {
        List<PlayHistoryEntry> filteredList = filterEntries(from, to, null, null);

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
        Map<Song, Integer> topTracks = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(n, entryList.size()); i++) {
            topTracks.put(entryList.get(i).getKey(), entryList.get(i).getValue());
        }
        return topTracks;
    }

    @Override
    public Map<Artist, Integer> topArtists(int n, LocalDateTime from, LocalDateTime to) {
        List<PlayHistoryEntry> filteredList = filterEntries(from, to, null, null);

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
        Map<Artist, Integer> topArtists = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(n, entryList.size()); i++) {
            topArtists.put(entryList.get(i).getKey(), entryList.get(i).getValue());
        }
        return topArtists;
    }

    @Override
    public Map<Playlist, Double> lowActivity(LocalDateTime from, LocalDateTime to, int percentThreshold) {
        /* The Integer.MAX_VALUE is a "hack" that forces the return to be entryList.size()
         so i get every playlist and not just the first X from topPlaylists */
        Map<Playlist, Integer> mapOfPlaylists = topPlaylists(Integer.MAX_VALUE, from, to);

        // Jetbrains suggestion - I cant use a regular if in a forEach
        AtomicInteger totalPlays = new AtomicInteger();
        mapOfPlaylists.forEach((_, e) -> totalPlays.addAndGet(e));

        Map<Playlist, Double> playlistActivityPercent = new LinkedHashMap<>();
        mapOfPlaylists.forEach((p, e) -> {
            double curr = (e.doubleValue() / totalPlays.get()) * 100;
            if (curr < percentThreshold) {
                playlistActivityPercent.put(p, curr);
            }
        });

        return playlistActivityPercent;
    }
}
