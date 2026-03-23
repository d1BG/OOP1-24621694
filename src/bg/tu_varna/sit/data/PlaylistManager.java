package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Playlist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlaylistManager implements PlaylistActions, Serializable {
    private List<Playlist> playlists;
    public PlaylistManager() {
        playlists = new ArrayList<>();
    }

    @Override
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    @Override
    public void createPlaylist(Playlist newPlaylist) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(newPlaylist.getName())) {
                throw new PlaylistException("Playlist already exists");
            }
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

    @Override
    public Playlist getPlaylistByName(String name) {
        for (Playlist p : playlists) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new PlaylistException("Couldn't find playlist");
    }
}
