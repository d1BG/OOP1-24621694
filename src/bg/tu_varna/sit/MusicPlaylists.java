package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.List;

public class MusicPlaylists {
    private List<Playlist> playlists;
    private List<Song> songs;

    public MusicPlaylists() {
        this.playlists = new ArrayList<>();
        this.songs = new ArrayList<>();
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

    void addSong(String title, String artist, String duration, String album, String year, String genre){
        if (album.isEmpty()) { album = "N/A"; }

        if (year.isEmpty()) { year = "N/A"; }

        if (genre.isEmpty()) { genre = "N/A"; }

        for  (Song song : songs) {
            if (song.getTitle().equals(title) && song.getArtist().equals(artist)) {
                System.out.println("Song already exists");
                return;
            }
        }
        int songID = songs.size()+1;
        songs.add(new Song(songID, title, artist, duration, album, year, genre));
    }

    void removeSong(int id) {
        for (Song song : songs) {
            if (song.getID() == id) {
                songs.remove(song);
                return;
            }
        }
        System.out.println(id + " does not exist");
    }

    // TODO: Filtering by artist, genre, year
    public void listSongs(){
        for (Song song : songs) {
            System.out.println(song.toString());
        }
    }
}
