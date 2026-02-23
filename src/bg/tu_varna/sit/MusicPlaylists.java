package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.List;

public class MusicPlaylists {
    private List<Playlist> playlists;
    public MusicPlaylists() {
        this.playlists = new ArrayList<>();
    }

    public void createPlaylist(String name){
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                System.out.println(playlist.getName() + " already exists");
                return;
            }
        }
        playlists.add(new Playlist(name));
    }

    public void createPlaylist(String name, String description){
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                System.out.println(playlist.getName() + " already exists");
                return;
            }
        }
        playlists.add(new Playlist(name, description));
    }

    public void deletePlaylist(String name){
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                playlists.remove(playlist);
                return;
            }
        }
        System.out.println(name + " does not exist");
    }

    public void listPlaylists(){
        for (Playlist playlist : playlists) {
            System.out.println(playlist.getName());
        }
    }
}
