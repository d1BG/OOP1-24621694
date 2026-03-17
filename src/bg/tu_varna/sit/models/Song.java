package bg.tu_varna.sit.models;

import java.io.Serializable;
import java.util.Objects;

public class Song implements Serializable {
    // Required*
    private int ID;
    private String title;
    private String artist; // object?
    private TimeDuration duration;
    // Optional
    private String album;
    private String year;
    private Genre genre;


    public Song(int ID, String title, String artist, String duration) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = new TimeDuration(duration);
        this.album = "N/A";
        this.year = "N/A";
        this.genre = Genre.NA;
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
        return duration.toString();
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
        this.duration = new TimeDuration(duration);
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = Genre.fromName(genre);
    }

    public String songInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ")      .append(ID)      .append("\n");
        sb.append("Title: ")   .append(title)   .append("\n");
        sb.append("Artist: ")  .append(artist)  .append("\n");
        sb.append("Duration: ").append(duration).append("\n");
        sb.append("Album: ")   .append(album)   .append("\n");
        sb.append("Year: ")    .append(year)    .append("\n");
        sb.append("Genre: ")   .append(genre)   .append("\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ID: " + ID + ". " + title + " - " + artist;
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
