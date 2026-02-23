package bg.tu_varna.sit;

import java.util.Objects;

public class Song {
    // Required*
    private String ID;
    private String title;
    private String artist;
    private String duration;
    // Optional
    private String album;
    private String year;
    private String genre;

    public Song(String ID, String title, String artist, String duration) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public Song(String ID, String title, String artist, String duration, String album) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
    }

    public Song(String ID, String title, String artist, String duration, String album, String year) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.year = year;
    }

    public Song(String ID, String title, String artist, String duration, String album, String year, String genre) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.year = year;
        this.genre = genre;
    }


    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist);
    }
}
