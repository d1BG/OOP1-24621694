package bg.tu_varna.sit.data;

import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;

public class MusicData implements MusicPlaylists, Serializable {
    private SongActions songManager;
    private PlaylistActions playlistManager;

    public MusicData() {
        playlistManager = new PlaylistManager();
        songManager = new SongManager();
    }

    @Override
    public SongActions getSongActions() {
        return songManager;
    }

    @Override
    public PlaylistActions getPlaylistActions() {
        return playlistManager;
    }

    @Override
    public void addSongToPlaylist(String playlistName, int songId, Integer position) {
        for (Playlist pl : getPlaylistActions().getPlaylists()) {
            if (pl.getName().equals(playlistName)) {
                for (Song song : getSongActions().getSongs()) {
                    if (song.getID() == songId) {
                        if (position == null || position >= pl.getSongs().size() || position < 0) {
                            pl.getSongs().add(song);
                        } else {
                            pl.getSongs().add(position, song);
                        }
                        return;
                    }
                }
                throw new PlaylistException("Song with id " + songId + " not found");
            }
        }
        throw new PlaylistException("Playlist " + playlistName + " not found");
    }

    @Override
    public void removeSongFromPlaylist(String playlistName, int songId) {
        for (Playlist pl : getPlaylistActions().getPlaylists()) {
            if (pl.getName().equals(playlistName)) {
                for (Song song : getSongActions().getSongs()) {
                    if (song.getID() == songId) {
                        pl.getSongs().remove(song);
                        return;
                    }
                }
                throw new PlaylistException("Song with id " + songId + " not found");
            }
        }
        throw new PlaylistException("Playlist " + playlistName + " not found");
    }

    @Override
    public void setMusicPlaylists(MusicPlaylists musicPlaylists) {
        this.playlistManager = musicPlaylists.getPlaylistActions();
        this.songManager = musicPlaylists.getSongActions();
    }

}
