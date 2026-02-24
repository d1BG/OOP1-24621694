package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.List;

public class MusicPlaylists {
    private List<Playlist> playlists;
    private List<Song> songs;

    public MusicPlaylists() {
        this.playlists = new ArrayList<>();
        this.songs = new ArrayList<>();
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
