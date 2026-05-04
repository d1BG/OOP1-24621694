package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.exceptions.PlaylistException;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;

import java.io.Serializable;
import java.util.*;

/**
 * Мениджър клас за плейлисти и операции с тях.
 * Имлементира {@code Serializable} за да може да се запазвя чрез ObjectOutputStream/InputObjectStream.
 */
public class PlaylistManager implements PlaylistActions, Serializable {
    /**
     * Колекция от плейлисти менижирани от класа.
     */
    private List<Playlist> playlists;

    /**
     * Конструктор за PlaylistManager, създава празен списък за плейлисти.
     */
    public PlaylistManager() {
        playlists = new ArrayList<>();
    }

    /**
     * @return Листата от плейлисти
     */
    @Override
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Добася нов плейлист в мениджъра.
     * @param newPlaylist плейлиста който добавя
     * @throws PlaylistException ако вече съществува
     */
    @Override
    public void createPlaylist(Playlist newPlaylist) throws PlaylistException {
        for (Playlist playlist : playlists) {
            if (playlist.equals(newPlaylist)) {
                throw new PlaylistException("Playlist already exists");
            }
        }
        playlists.add(newPlaylist);
    }

    /**
     * Премахва/Изтрива плейлист от мениджура
     * @param playlist плейлиста който премахва
     * @throws PlaylistException ако не съществува.
     */
    @Override
    public void deletePlaylist(Playlist playlist) throws PlaylistException {
        if (playlists.remove(playlist)) {
            return;
        }
        throw new PlaylistException("Playlist does not exist");
    }

    /**
     * Разбърква плейлиста
     * @param playlist плейлиста който да бъде разбъркан
     * @param seed seed на произволност
     */
    @Override
    public void shuffle(Playlist playlist, Integer seed){
        Random rand = new Random();
        if (seed != null) {rand.setSeed(seed);}
        Collections.shuffle(playlist.getSongs(), rand);
    }

    @Override
    public void move(Playlist playlist, int fromPos, int toPos) {
        Song movedSong = playlist.getSongs().remove(fromPos);
        playlist.getSongs().add(toPos, movedSong);
    }

    /**
     * Намира плейлист по име
     * @param name име на плейлиста който търсим
     * @return намерения плейлист
     * @throws PlaylistException ако плейлиста не е намерен
     */
    @Override
    public Playlist getPlaylistByName(String name) throws PlaylistException {
        for (Playlist p : playlists) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new PlaylistException("Couldn't find playlist");
    }
}
