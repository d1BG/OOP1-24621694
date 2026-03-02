package bg.tu_varna.sit.data;

import bg.tu_varna.sit.models.Playlist;

import java.util.List;

public interface PlaylistActions {
    List<Playlist> getPlaylists();
    void createPlaylist(String name, String description);
}
