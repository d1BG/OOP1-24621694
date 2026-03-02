package bg.tu_varna.sit.data;

import bg.tu_varna.sit.models.Song;

import java.util.List;

public interface SongActions {
    List<Song> getSongs();
    void addSong(Song song);
    void removeSong(int id);
}
