package bg.tu_varna.sit.data;

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
}
