package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.*;
import bg.tu_varna.sit.models.*;

import java.io.Serializable;
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

    @Override
    public void addSongToPlaylist(String playlistName, int songId, Integer position) { // TODO: pass playlist and song object - fetch them in command
        Playlist pl = getPlaylistActions().getPlaylistByName(playlistName);
        Song song = getSongActions().getSongById(songId);

        if (position == null || position >= pl.getSongs().size() || position < 0) {
            pl.getSongs().add(song);
        } else {
            pl.getSongs().add(position, song);
        }
    }

    @Override
    public void removeSongFromPlaylist(String playlistName, int songId) { // TODO: pass playlist and song object - fetch them in command
        Playlist pl = getPlaylistActions().getPlaylistByName(playlistName);
        Song song = getSongActions().getSongById(songId);
        pl.getSongs().remove(song);
    }

    @Override
    public void setMusicPlaylists(MusicPlaylists musicPlaylists) {
        this.playlistManager = musicPlaylists.getPlaylistActions();
        this.songManager = musicPlaylists.getSongActions();
        this.playHistoryManager = musicPlaylists.getPlayHistoryActions();
        this.artistManager = musicPlaylists.getArtistActions();
    }

    @Override
    public void dropPlaylist(Playlist p) {
        List<PlayHistoryEntry> filterList = playHistoryManager.filterEntries(null, null, p, null);
        playHistoryManager.getEntries().removeAll(filterList);
        playlistManager.deletePlaylist(p);
    }

    @Override
    public void removeArtistByUsername(Artist artist) {
        getSongActions().getSongs().removeAll(getSongActions().filterSongs(artist, null, null));
        artistManager.removeArtist(artist);
    }
}
