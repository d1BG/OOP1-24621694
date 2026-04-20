package bg.tu_varna.sit.data.interfaces;

import bg.tu_varna.sit.models.Playlist;

import java.util.List;

public interface PlaylistActions {
    List<Playlist> getPlaylists();
    void createPlaylist(Playlist playlist);
    void deletePlaylist(Playlist p);
    void shuffle(Playlist playlist, Integer seed);
    void move(Playlist playlist, int fromPos, int toPos);
    Playlist getPlaylistByName(String name);
}
