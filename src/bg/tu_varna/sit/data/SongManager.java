package bg.tu_varna.sit.data;

import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.models.Song;

import java.util.ArrayList;
import java.util.List;

public class SongManager implements SongActions {
    private List<Song> songs;
    public SongManager() {
        songs = new ArrayList<>();
    }

    public List<Song> getSongs() {
        return songs;
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
    public void removeSong(int id) {
        for (Song song : songs) {
            try {
                if (song.getID() == id) {
                    songs.remove(song);
                    return;
                }
            } catch (NumberFormatException e) {
                throw new SongException("Please input a number");
            }
        }
    }
}
