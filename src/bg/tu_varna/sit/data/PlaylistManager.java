package bg.tu_varna.sit.data;

import bg.tu_varna.sit.exceptions.PlaylistException;
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

    @Override
    public void createPlaylist(String name, String description) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                throw new PlaylistException("Playlist already exists");
            }
        }

        Playlist newPlaylist = new Playlist(name);
        if (!description.isEmpty()) {
            newPlaylist.setDescription(description);
        }

        playlists.add(newPlaylist);
    }

    @Override
    public void deletePlaylist(String name) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                playlists.remove(playlist);
                return;
            }
        }
        throw new PlaylistException("Playlist does not exist");
    }
}
