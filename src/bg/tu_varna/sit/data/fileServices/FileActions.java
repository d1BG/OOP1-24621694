package bg.tu_varna.sit.data.fileServices;

import bg.tu_varna.sit.data.MusicPlaylists;

import java.io.File;

public interface FileActions {
    boolean open(MusicPlaylists musicPlaylists, File file);
    void close(MusicPlaylists musicPlaylists);

    boolean write(MusicPlaylists musicPlaylists, File file);

    boolean isOpen();

    File getFile();
}
