package bg.tu_varna.sit.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist implements Serializable {
    private String name;
    private String description;
    private List<Song> songs;

    /**
     * Конструктор за обкет {@code Playlist}
     * @param name име на плейлиста.
     */
    public Playlist(String name){
        this.name = name;
        this.description = "N/A";
        this.songs = new ArrayList<>();
    }

    /**
     * @return Име на плейлиста
     */
    public String getName() {
        return name;
    }

    /**
     * @return Описание на плейлиста
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description {@code String} за описание на плейлиста
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Списък с песните в плейлиста
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * метод за сравнение между два обекта
     * @param obj обекта с който се прави сравнение.
     * @return boolean определящ равенство между този обект и {@code obj}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Playlist playlist = (Playlist) obj;
        return Objects.equals(name, playlist.name);
    }

    /**
     * Метод проверяващ дали плейлиста съдържа подадената песен
     * @param song Песента която се проверява дали е в плейлиста.
     * @return {@code boolean}, истина ако песента е в плейлиста, лъжа ако не.
     */
    public boolean contains(Song song) {
        for (Song s : songs) {
            if (s.equals(song)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Хеш на обекта по име
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
