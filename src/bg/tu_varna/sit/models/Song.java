package bg.tu_varna.sit.models;

import java.io.Serializable;
import java.util.Objects;

public class Song implements Serializable {
    // Required*
    private int ID;
    private String title;
    private Artist artist; // object?
    private TimeDuration duration;
    // Optional
    private String album;
    private String year;
    private Genre genre;


    public Song(int ID, String title, Artist artist, String duration, String album, String year, Genre genre) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = new TimeDuration(duration);
        this.album = album != null ? album : "N/A";
        this.year = year != null ? year : "N/A";
        this.genre = genre;
    }


    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public TimeDuration getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public Genre getGenre() {
        return genre;
    }

    public String songInfo(){
        return  "ID: "       + ID       + "\n" +
                "Title: "    + title    + "\n" +
                "Artist: "   + artist   + "\n" +
                "Duration: " + duration + "\n" +
                "Album: "    + album    + "\n" +
                "Year: "     + year     + "\n" +
                "Genre: "    + genre    + "\n";
    }

    @Override
    public String toString() {
        return "ID: " + ID + ". " + title + " - " + artist + " " + duration;
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
