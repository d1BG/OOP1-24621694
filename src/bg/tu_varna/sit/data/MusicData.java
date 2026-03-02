package bg.tu_varna.sit.data;

import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicData implements MusicPlaylists {
    private PlaylistManager playlistManager;
    private SongManager songManager;

    public MusicData() {
        playlistManager = new PlaylistManager();
        songManager = new SongManager();
    }
    @Override
    public List<Playlist> getPlaylists() {
        return playlistManager.getPlaylists();
    }

    @Override
    public List<Song> getSongs() {
        return songManager.getSongs();
    }
}
