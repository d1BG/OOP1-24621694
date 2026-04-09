package bg.tu_varna.sit.data.fileServices;

import bg.tu_varna.sit.data.interfaces.MusicPlaylists;

import java.io.File;

public interface FileActions {
    void open(MusicPlaylists musicPlaylists, File file);
    void close(MusicPlaylists musicPlaylists);

    void write(MusicPlaylists musicPlaylists, File file);

    boolean isOpen();

    File getFile();
}
