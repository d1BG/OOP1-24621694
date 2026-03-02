package bg.tu_varna.sit.data;

public class MusicData implements MusicPlaylists {
    private SongManager songManager;
    private PlaylistManager playlistManager;

    public MusicData() {
        playlistManager = new PlaylistManager();
        songManager = new SongManager();
    }

    @Override
    public SongManager getSongManager() {
        return songManager;
    }

    @Override
    public PlaylistActions getPlaylistActions() {
        return playlistManager;
    }

}
