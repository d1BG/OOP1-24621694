package bg.tu_varna.sit.data;

public interface MusicPlaylists {
    SongActions getSongActions();
    PlaylistActions getPlaylistActions();

    void setMusicPlaylists(MusicPlaylists musicPlaylists);
}