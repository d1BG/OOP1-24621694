package bg.tu_varna.sit.data.interfaces;

import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

public interface MusicPlaylists {
    SongActions getSongActions();
    ArtistActions getArtistActions();
    PlaylistActions getPlaylistActions();
    PlayHistoryActions getPlayHistoryActions();

    void addSongToPlaylist(Playlist playlist, Song song, Integer position);
    void removeSongFromPlaylist(Playlist playlist, Song song);
    void dropPlaylist(Playlist p);
    void removeArtist(Artist artist);

    void setMusicPlaylists(MusicPlaylists musicPlaylists);
}