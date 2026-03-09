package bg.tu_varna.sit.data;

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
    public void setMusicPlaylists(MusicPlaylists musicPlaylists) {
        this.playlistManager = musicPlaylists.getPlaylistActions();
        this.songManager = musicPlaylists.getSongActions();
    }

}
