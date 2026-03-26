package bg.tu_varna.sit.data;

import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.exceptions.ArtistException;
import bg.tu_varna.sit.models.Artist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArtistManager implements Serializable, ArtistActions {
    List<Artist> artists;
    public ArtistManager() {
        artists = new ArrayList<>();
    }

    @Override
    public Artist getArtistByUsername(String username) {
        for (Artist a : artists) {
            if (a.getUsername().equals(username)){
                return a;
            }
        }
        throw new ArtistException("Artist not found");
    }

    @Override
    public void addArtist(Artist artist) {
        for (Artist a : artists) {
            if (a.equals(artist)) {
                throw new ArtistException("Artist already exists");
            }
        }
        artists.add(artist);
    }

    @Override
    public void removeArtist(Artist artist) {
        artists.remove(artist);
    }

    @Override
    public List<Artist> getArtists() {
        return artists;
    }
}
