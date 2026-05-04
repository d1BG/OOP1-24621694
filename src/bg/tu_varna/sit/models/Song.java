package bg.tu_varna.sit.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Клас заа обект описващ песен с задължителни уникални идентификатори, заглавия, артисти и продължителност
 * и незадължителни албум, година и жанр. Имлементира {@code Serializable} за да може да се запазвя
 * чрез ObjectOutputStream/InputObjectStream.
 */
public class Song implements Serializable {
    /**
     * Уникален идентификатор на песента (задължително)
     */
    private int ID;

    /**
     * Заглавие на песента (задължително)
     */
    private String title;

    /**
     * Изпълнител на песента (задължително)
     */
    private Artist artist;

    /**
     * Продължителност на песента в формат MM:SS (задължително)
     */
    private TimeDuration duration;

    /**
     * Албум на песента
     */
    private String album;

    /**
     * Година на излизане на песента
     */
    private String year;

    /**
     * Жанр на песента
     */
    private Genre genre;

    /**
     * Конструктор за обект Песен.
     * @param ID int Уникален идентификатор на песен - задължителен
     * @param title String Име на песента - задължителен
     * @param artist Обект Artist - задължителен
     * @param duration Обект TimeDuration - задължителен
     * @param album String име на албума на песента
     * @param year String година на излизане на песента
     * @param genre enum стойност от Genre на песента
     */
    public Song(int ID, String title, Artist artist, TimeDuration duration, String album, String year, Genre genre) {
        this.ID = ID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album != null ? album : "N/A";
        this.year = year != null ? year : "N/A";
        this.genre = genre;
    }

    /**
     * @return int уникален идентификатор на песента
     */
    public int getID() {
        return ID;
    }

    /**
     * @return String Името на песента
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Обект Artist на песента
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * @return обект TimeDuration на песента
     */
    public TimeDuration getDuration() {
        return duration;
    }

    /**
     * @return String Албума на песента
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @return String Годината на песента
     */
    public String getYear() {
        return year;
    }

    /**
     * @return enum стойност от тип Genre на песента
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * @return String с информация за всички полета на песента
     */
    public String songInfo(){
        return  "ID: "       + ID       + "\n" +
                "Title: "    + title    + "\n" +
                "Artist: "   + artist   + "\n" +
                "Duration: " + duration + "\n" +
                "Album: "    + album    + "\n" +
                "Year: "     + year     + "\n" +
                "Genre: "    + genre    + "\n";
    }

    /**
     * @return {@code String} с информацията от всички задължителни полета на песента
     */
    @Override
    public String toString() {
        return "ID: " + ID + ". " + title + " - " + artist + " " + duration;
    }

    /**
     * Метод за равенство (по име и артист)
     * @param obj обекта с който се прави сравнение.
     * @return {@code boolean} определящ равенство между този обект и {@code obj}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Song song = (Song) obj;
        return Objects.equals(title, song.title) && Objects.equals(artist, song.artist);
    }

    /**
     * @return Хеш стойност на обекта по име и артист
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, artist);
    }
}
