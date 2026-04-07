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

public class SongManager implements SongActions, Serializable {
    private List<Song> songs;
    public SongManager() {
        songs = new ArrayList<>();
    }

    public List<Song> getSongs() {
        return songs;
    }

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

    @Override
    public void addSong(Song song) {
        for (Song s : songs) {
            if (s.getTitle().equals(song.getTitle()) &&  s.getArtist().equals(song.getArtist()) ) {
                throw new SongException("Song already exists");
            }
        }
        songs.add(song);
    }

    @Override
    public void removeSong(Song song) {
        songs.remove(song);
    }

    @Override
    public Song getSongById(int id) {
        for (Song song : songs) {
            if (song.getID() == id) {
                return song;
            }
        }
        throw new SongException("Song not found");
    }
}
