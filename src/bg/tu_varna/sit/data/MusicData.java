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

    /**
     * Конструктор за {@code MusicData} инциализиращ всички мениджъри
     */
    public MusicData() {
        playlistManager = new PlaylistManager();
        songManager = new SongManager();
        artistManager = new ArtistManager();
        playHistoryManager = new PlayHistoryManager();
    }

    /**
     * @return мениджъра за песни
     */
    @Override
    public SongActions getSongActions() {
        return songManager;
    }

    /**
     * @return мениджъра за артисти
     */
    @Override
    public ArtistActions getArtistActions() {
        return artistManager;
    }

    /**
     * @return мениджъра за плейлисти
     */
    @Override
    public PlaylistActions getPlaylistActions() {
        return playlistManager;
    }

    /**
     * @return мениджъра за пускания
     */
    @Override
    public PlayHistoryActions getPlayHistoryActions() {
        return playHistoryManager;
    }

    /**
     * Добавя песен в плейлист
     * @param playlist плейлиста в който добавяме песен
     * @param song песента която добавяме
     * @param position позицията в която да я добавим (може да е {@code null})
     */
    @Override
    public void addSongToPlaylist(Playlist playlist, Song song, Integer position) {
        if (position == null || position >= playlist.getSongs().size() || position < 0) {
            playlist.getSongs().add(song);
        } else {
            playlist.getSongs().add(position, song);
        }
    }

    /**
     * Премахва песен от плейлист
     * @param playlist плейлиста от който премахваме песен
     * @param song песента която премахваме
     */
    @Override
    public void removeSongFromPlaylist(Playlist playlist, Song song) {
        playlist.getSongs().remove(song);
    }

    /**
     * Зарежда информация от дръг {@code MusicPlaylists} обект
     * @param musicPlaylists мениджъра от който се зарежда информацията
     */
    @Override
    public void setMusicPlaylists(MusicPlaylists musicPlaylists) {
        this.playlistManager = musicPlaylists.getPlaylistActions();
        this.songManager = musicPlaylists.getSongActions();
        this.playHistoryManager = musicPlaylists.getPlayHistoryActions();
        this.artistManager = musicPlaylists.getArtistActions();
    }

    /**
     * Премахва всички пускания от някой плейлист и го изтрива
     * @param playlist плейлиста които изтрива
     */
    @Override
    public void dropPlaylist(Playlist playlist) {
        List<PlayHistoryEntry> filterList = playHistoryManager.filterEntries(null, null, playlist, null);
        playHistoryManager.getEntries().removeAll(filterList);
        playlistManager.deletePlaylist(playlist);
    }

    /**
     * Премахва всички песни от даден артист и изтрива артиста
     * @param artist Артиста който изтрива
     */
    @Override
    public void removeArtist(Artist artist) {
        getSongActions().getSongs().removeAll(getSongActions().filterSongs(artist, null, null));
        artistManager.removeArtist(artist);
    }
}
