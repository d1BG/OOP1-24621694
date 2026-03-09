package bg.tu_varna.sit.data;

import java.io.File;

public interface FileActions {
    boolean open(File file);
    void close();

    boolean write(MusicPlaylists musicPlaylists, File file);

    boolean isOpen(File file);
    boolean isOpen();

    File getFile();
}
