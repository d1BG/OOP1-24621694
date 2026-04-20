package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.exceptions.ArtistException;
import bg.tu_varna.sit.models.Artist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArtistManager implements Serializable, ArtistActions {
    List<Artist> artists;

    /**
     * Конструктор за {@code ArtistManager} създаващ празна листа от артисти
     */
    public ArtistManager() {
        artists = new ArrayList<>();
    }

    /**
     * Намира артист по уникален псевдоним
     * @param username уникален псевдоним по който търсим
     * @return артист намерен от псевдоним
     * @throws ArtistException ако артиста не е намерен
     */
    @Override
    public Artist getArtistByUsername(String username) throws ArtistException {
        for (Artist a : artists) {
            if (a.getUsername().equals(username)){
                return a;
            }
        }
        throw new ArtistException("Artist not found");
    }

    /**
     * Добавя артист
     * @param artist артиста който добавя
     * @throws ArtistException ако артиста вече съществува
     */
    @Override
    public void addArtist(Artist artist) throws ArtistException {
        for (Artist a : artists) {
            if (a.equals(artist)) {
                throw new ArtistException("Artist already exists");
            }
        }
        artists.add(artist);
    }

    /**
     * Премахва артист
     * @param artist артиста който премахва
     */
    @Override
    public void removeArtist(Artist artist) {
        artists.remove(artist);
    }

    /**
     * @return Листа от артисти
     */
    @Override
    public List<Artist> getArtists() {
        return artists;
    }
}
