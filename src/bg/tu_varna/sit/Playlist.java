package bg.tu_varna.sit;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private String name;
    private String description;
    private List<Song> songs;

    public Playlist(String name){
        this.name = name;
        this.description = "N/A";
    }

    public Playlist(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(name, playlist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
