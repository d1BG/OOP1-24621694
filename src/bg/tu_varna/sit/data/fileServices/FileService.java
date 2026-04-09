package bg.tu_varna.sit.data.fileServices;


import bg.tu_varna.sit.data.MusicData;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.FileException;

import java.io.*;

public class FileService implements FileActions {
    private File file;

    public FileService() {}

    @Override
    public void open(MusicPlaylists musicPlaylists, File file) {
        if (isOpen()) {
            throw new FileException("A File is already open, save/close it before opening a new one");
        }

        if (file.exists()) {
            this.file = file;

            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                musicPlaylists.setMusicPlaylists((MusicPlaylists) ois.readObject());
                ois.close();
                return;
            } catch (ClassNotFoundException | IOException e) {
                throw new FileException(e.getMessage());
            }
        }
        throw new FileException("This file doesnt exist");
    }

    @Override
    public void close(MusicPlaylists musicPlaylists) {
        if (!isOpen()) {
            throw new FileException("There is no open file");
        }
        this.file = null;
        musicPlaylists.setMusicPlaylists(new MusicData());
    }

    @Override
    public void write(MusicPlaylists musicPlaylists, File file) {
        if (file == null) {
            if (this.file != null) {
                file = this.file; // if no file is passed, use the already opened one
            } else {
                throw new FileException("There is no open file");
            }
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch ( IOException e ) {
            throw new FileException("Could not create new file: " + e.getMessage());
        }

        try {
            this.file = file;
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(musicPlaylists);
            oos.close();
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }

    @Override
    public boolean isOpen() {
        return (file != null && file.exists() && file.isFile());
    }

    @Override
    public File getFile() {
        return file;
    }
}
