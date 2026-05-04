package bg.tu_varna.sit.data.fileServices;


import bg.tu_varna.sit.data.MusicData;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.FileException;

import java.io.*;

/**
 * Клас за работа са файлове.
 */
public class FileService implements FileActions {
    /**
     * Файла с който програмата работи.
     */
    private File file;

    /**
     * Конструктор за услуга за работа с файлове
     */
    public FileService() {}

    /**
     * Отваря файл и зарежда информацията от него в подадения мениджър файл.
     * @param musicPlaylists мениджър файла в който се зарежда информацията от подадения файл
     * @param file файла от който се зарежда информация.
     */
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

    /**
     * Затваря отворения файл, и изчиствав информацията в програмата.
     * @param musicPlaylists мениджър класа който да бъде изчистен.
     */
    @Override
    public void close(MusicPlaylists musicPlaylists) {
        if (!isOpen()) {
            throw new FileException("There is no open file");
        }
        this.file = null;
        musicPlaylists.setMusicPlaylists(new MusicData());
    }

    /**
     * Записва информацията в опреден файл или в вече отворения файл
     * @param musicPlaylists обекта който бива записван в файла
     * @param file файла в който записва
     * @throws FileException ако няма отворен файл, ако не може да се създаде нов файл или има грешка при писане
     */
    @Override
    public void write(MusicPlaylists musicPlaylists, File file) throws FileException{
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

    /**
     * Проверява дали има отворен файл
     * @return истина ако е отворен, съществъва и е файл.
     */
    @Override
    public boolean isOpen() {
        return (file != null && file.exists() && file.isFile());
    }

    /**
     * @return отворения файл (може да е {@code null})
     */
    @Override
    public File getFile() {
        return file;
    }
}
