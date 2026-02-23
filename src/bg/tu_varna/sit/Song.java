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

    /*
        https://stackoverflow.com/questions/965690/how-do-i-use-optional-parameters-in-java
        I used an array of optional arguments, the other option was to use Overloading, but
        I didnt want to repeat code and have 4 different constructors for essentially the
        same thing
     */

    public Song(String ID, String title, String artist, String duration, String... args) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        if (args.length > 0) {
            if (args[0] != null) {
                this.album = args[0];
            } else {
                this.album = "N/A";
                this.year = "N/A";
                this.genre = "N/A";
                return;
            }

            if (args[1] != null) {
                this.year = args[1];
            }  else {
                this.year = "N/A";
                this.genre = "N/A";
                return;
            }

            if (args[2] != null) {
                this.genre = args[2];
            } else {
                this.genre = "N/A";
            }
        }
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
