package bg.tu_varna.sit.data;

import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicData implements MusicPlaylists {
    private List<Playlist> playlists;
    private List<Song> songs;

    public MusicData() {
        this.playlists = new ArrayList<>();
        this.songs = new ArrayList<>();
    }
    @Override
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    @Override
    public List<Song> getSongs() {
        return songs;
    }
}
