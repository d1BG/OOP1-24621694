package bg.tu_varna.sit.data.interfaces;

import bg.tu_varna.sit.models.Playlist;

import java.util.List;

public interface PlaylistActions {
    List<Playlist> getPlaylists();
    void createPlaylist(Playlist playlist);
    Playlist getPlaylistByName(String name);
    void deletePlaylist(Playlist p);
}
