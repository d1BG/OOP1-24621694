package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.data.interfaces.SongActions;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;
import java.util.List;

public class MusicData implements MusicPlaylists, Serializable {
    private SongActions songManager;
    private PlaylistActions playlistManager;
    private PlayHistoryActions playHistoryManager;

    public MusicData() {
        playlistManager = new PlaylistManager();
        songManager = new SongManager();
        playHistoryManager = new PlayHistoryManager();
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
    public PlayHistoryActions getPlayHistoryActions() {
        return playHistoryManager;
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

    @Override
    public void dropPlaylist(Playlist p) {
        List<PlayHistoryEntry> filterList = playHistoryManager.filterEntries(null, null, p, null);
        playHistoryManager.getEntries().removeAll(filterList);
        playlistManager.deletePlaylist(p);
    }
}
