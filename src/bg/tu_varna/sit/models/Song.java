package bg.tu_varna.sit.models;

import java.util.Objects;

public class Song {
    // Required*
    private int ID;
    private String title;
    private String artist;
    private String duration;
    // Optional
    private String album;
    private String year;
    private String genre;


    public Song(int ID, String title, String artist, String duration) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = "N/A";
        this.year = "N/A";
        this.genre = "N/A";
    }


    public int getID() {
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                '}';
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
