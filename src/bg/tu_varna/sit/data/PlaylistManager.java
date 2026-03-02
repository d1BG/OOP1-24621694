package bg.tu_varna.sit.data;

import bg.tu_varna.sit.models.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistManager implements PlaylistActions {
    List<Playlist> playlists;
    public PlaylistManager() {
        playlists = new ArrayList<>();
    }

    @Override
    public List<Playlist> getPlaylists() {
        return playlists;
    }
}
