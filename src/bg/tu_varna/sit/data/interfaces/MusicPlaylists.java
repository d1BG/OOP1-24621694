package bg.tu_varna.sit.data.interfaces;

public interface MusicPlaylists {
    SongActions getSongActions();
    PlaylistActions getPlaylistActions();
    PlayHistoryActions getPlayHistoryActions();

    void addSongToPlaylist(String playlistName, int songId, Integer position);
    void removeSongFromPlaylist(String playlistName, int songId);

    void setMusicPlaylists(MusicPlaylists musicPlaylists);
}