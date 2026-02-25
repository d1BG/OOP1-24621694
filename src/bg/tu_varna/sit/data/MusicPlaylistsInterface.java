package bg.tu_varna.sit.data;

import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.models.Playlist;
import java.util.List;

public interface MusicPlaylistsInterface {
    List<Song> getSongs();
    List<Playlist> getPlaylists();
}