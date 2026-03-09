package bg.tu_varna.sit.data;


import bg.tu_varna.sit.exceptions.FileException;

import java.io.*;

public class FileService implements FileActions {
    private File file;
    public FileService(File file) {
        this.file = file;
    }

    public FileService(){}


    // TODO: open file should load the file
    @Override
    public boolean open(File file) {
        if (isOpen()) {
            throw new FileException("A File is already open");
        }

        if (file.exists()) {
            this.file = file;
            return true;
        }
        return false;
    }

    @Override
    public void close() {
        if (!isOpen()) {
            throw new FileException("There is no open file");
        }
        this.file = null;
    }

    @Override
    public boolean write(MusicPlaylists musicPlaylists, File file) {
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
            throw new FileException("Could not create new file");
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(musicPlaylists);
            oos.close();
            return true;
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }

    @Override
    public boolean isOpen() {
        return (file != null && file.exists() && file.isFile());
    }

    @Override
    public boolean isOpen(File file) {
        return (file.exists() && file.isFile());
    }

    @Override
    public File getFile() {
        return file;
    }
}
