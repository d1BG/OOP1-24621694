package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.*;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.PlayHistoryEntry;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MusicData implements MusicPlaylists, Serializable {
    private SongActions songManager;
    private ArtistActions artistManager;
    private PlaylistActions playlistManager;
    private PlayHistoryActions playHistoryManager;

    public MusicData() {
        playlistManager = new PlaylistManager();
        songManager = new SongManager();
        artistManager = new ArtistManager();
        playHistoryManager = new PlayHistoryManager();
    }

    @Override
    public SongActions getSongActions() {
        return songManager;
    }

    @Override
    public ArtistActions getArtistActions() {
        return artistManager;
    }

    @Override
    public PlaylistActions getPlaylistActions() {
        return playlistManager;
    }

    @Override
    public PlayHistoryActions getPlayHistoryActions() {
        return playHistoryManager;
    }

    // TODO: use getPlaylistByName and getSongById
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

    // TODO: use getPlaylistByName and getSongById
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

    @Override
    public void removeArtistByUsername(String username) {
        Artist artist = getArtistActions().getArtistByUsername(username);
        getSongActions().getSongs().removeAll(getSongActions().filterSongs(artist, null, null));
    }
}
