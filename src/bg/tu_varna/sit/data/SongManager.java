package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.SongException;
// import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.Genre;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Мениджър клас за песни и операции с тях.
 * Имлементира {@code Serializable} за да може да се запазвя чрез ObjectOutputStream/InputObjectStream.
 */
public class SongManager implements SongActions, Serializable {
    /**
     * Колекция от песните менижирани от класа.
     */
    private List<Song> songs;

    /**
     * Конструктор за SongManager, суздава празен списък за песни
     */
    public SongManager() {
        songs = new ArrayList<>();
    }

    /**
     * Филтрира листата от песни по подаден Артист, Жанр и/или Година.
     * Ако са подадени {@code null} стойности, не се филтрира по тези обекти.
     * @param artist Артисст по който се филтрира
     * @param genre Жанр по който се филтрира
     * @param year Година по която се филтрира
     * @return филтрирана листа по подадените аргументи
     */
    @Override
    public List<Song> filterSongs(Artist artist, Genre genre, String year) {
        List<Song> filteredSongs = new ArrayList<>(songs);

        if (artist != null) {
            filteredSongs.removeIf(s -> !s.getArtist().equals(artist));
        }

        if (genre != null) {
            filteredSongs.removeIf(s -> !s.getGenre().equals(genre));
        }

        if (year != null) {
            filteredSongs.removeIf(s -> !s.getYear().equals(year));
        }

        return filteredSongs;
    }

    /**
     * Добавяне на песен.
     * @param song песента аз добавяне
     * @throws SongException ако песента съшествува
     */
    @Override
    public void addSong(Song song) throws SongException {
        for (Song s : songs) {
            if (s.equals(song)) {
                throw new SongException("Song already exists");
            }
        }
        songs.add(song);
    }

    /**
     * Премахва песен.
     * @param song песен за премахване
     */
    @Override
    public void removeSong(Song song) {
        songs.remove(song);
    }

    /**
     * Намира песен по уникален идентификатор
     * @param id уникален идентификатор по който се търси
     * @return песента отговаряща на уникалния идентификатор
     * @throws SongException ако песента не е намерена
     */
    @Override
    public Song getSongById(int id) throws SongException {
        for (Song song : songs) {
            if (song.getID() == id) {
                return song;
            }
        }
        throw new SongException("Song not found");
    }

    /**
     * @return уникален идентификатор за следващата песен която се добавя.
     */
    public int getNextSongID() {
        int songID;
        if (songs.isEmpty()) {
            songID = 1;
        } else {
            songID = songs.getLast().getID()+1;
        }
        return songID;
    }

    /**
     * @return листа от Песни.
     */
    public List<Song> getSongs() {
        return songs;
    }
}
