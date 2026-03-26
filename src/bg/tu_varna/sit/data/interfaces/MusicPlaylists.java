package bg.tu_varna.sit.data.interfaces;

import bg.tu_varna.sit.models.Playlist;

public interface MusicPlaylists {
    SongActions getSongActions();
    ArtistActions getArtistActions();
    PlaylistActions getPlaylistActions();
    PlayHistoryActions getPlayHistoryActions();

    void addSongToPlaylist(String playlistName, int songId, Integer position);
    void removeSongFromPlaylist(String playlistName, int songId);
    void dropPlaylist(Playlist p);
    void removeArtistByUsername(String username);

    void setMusicPlaylists(MusicPlaylists musicPlaylists);
}