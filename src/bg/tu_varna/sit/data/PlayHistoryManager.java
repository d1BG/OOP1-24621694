package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Мениджър клас за пускания на песни и операции с тях.
 * Имлементира {@code Serializable} за да може да се запазвя чрез ObjectOutputStream/InputObjectStream.
 */
public class PlayHistoryManager implements Serializable, PlayHistoryActions {
    /**
     * Колекция от пускания.
     */
    private List<PlayHistoryEntry> entries;

    /**
     * Конструктор за {@code PlayHistoryManager} създаващ празна листа от пускания
     */
    public PlayHistoryManager() {
        this.entries = new ArrayList<>();
    }

    /**
     * @return Връща списъка с пусканията
     */
    public List<PlayHistoryEntry> getEntries() {
        return entries;
    }

    /**
     * Пуска избрана песен
     * @param song Песента която пуска
     * @param playlist Плейлист от който е песента която се пуска (може да е {@code null})
     * @throws PlaylistException ако песента не е част от пл
     */
    @Override
    public void play(Song song, Playlist playlist) throws PlaylistException {
        if (!playlist.contains(song)) {
            throw new PlaylistException("Song is not in the provided playlist");
        }
        entries.add(new PlayHistoryEntry(song, playlist));
    }

    /**
     * Пуска избрана песен
     * Използва се само от произволния генератор.
     * @param song песента която пуска
     * @param playlist плейлиста от който е песента (може да е {@code null})
     * @param timestamp време по което е пусната песента
     */
    public void play(Song song, Playlist playlist, LocalDateTime timestamp) {
        entries.add(new PlayHistoryEntry(song, playlist, timestamp));
    }

    /**
     * Филтрира списъка от пускания по определени категории
     * Ако на филтър бъде подаден {@code null} няма да се филтрира по тази категория
     * @param from филтър от коя дата/кой час са пусканията
     * @param to филтър до коя дата/кой час са пусканията
     * @param playlist филтър по плейлист
     * @param song филтър по песен
     * @return филтриран списък по подадените аргументи
     */
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

    /**
     * Топ {@code N} на брой пускания по плейлисти
     * @param n топ колко да се върнат
     * @param from филтър от (може да е {@code null})
     * @param to филтър до (може да е {@code null})
     * @return връща {@code LinkedHashMap<Playlist, Integer>} подреден по слушания с {@code N} на брой елементи
     */
    @Override
    public Map<Playlist, Integer> topPlaylists(int n, LocalDateTime from, LocalDateTime to) {
        List<PlayHistoryEntry> filteredList = filterEntries(from, to, null, null);
        filteredList.removeIf(e-> e.getPlaylist() == null);

        Map<Playlist, Integer> counts = new HashMap<>();
        for (PlayHistoryEntry e : filteredList) {
            Playlist curr = e.getPlaylist();
            counts.put(curr, counts.getOrDefault(curr, 0) + 1); //TODO
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

    /**
     * Топ {@code N} на брой пускания по песни
     * @param n топ колко да се върнат
     * @param from филтър от (може да е {@code null})
     * @param to филтър до (може да е {@code null})
     * @return връща {@code LinkedHashMap<Song, Integer>} подреден по слушания с {@code N} на брой елементи
     */
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

    /**
     * Топ {@code N} на брой пускания по Артисти
     * @param n топ колко да се върнат
     * @param from филтър от (може да е {@code null})
     * @param to филтър до (може да е {@code null})
     * @return връща {@code LinkedHashMap<Artist, Integer>} подреден по слушания с {@code N} на брой елементи
     */
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

    /**
     * Извежда всички плейлисти между две дати под подаден процент слушаност за педиода
     * @param from филтър от
     * @param to филтър до
     * @param percentThreshold процент слушаност
     * @return {@code Map<Playlist, Double>} от всикчки плейлисти за периода под подадения процент слушаност
     */
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
