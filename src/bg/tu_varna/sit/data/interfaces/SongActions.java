package bg.tu_varna.sit.data.interfaces;

import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.Genre;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public interface SongActions {
    List<Song> getSongs();
    List<Song> filterSongs(Artist artist, Genre genre, String year);
    void addSong(Song song);
    void removeSong(int id);
    Song getSong(int id);
}
