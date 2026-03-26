package bg.tu_varna.sit.data.interfaces;

import bg.tu_varna.sit.models.Artist;

import java.util.List;

public interface ArtistActions {
    void addArtist(Artist artist);
    void removeArtist(Artist artist);
    Artist getArtistByUsername(String username);
    List<Artist> getArtists();
}
