package bg.tu_varna.sit;

import java.util.List;

public class AddsongCommand implements Command {
    MusicPlaylists musicPlaylists;
    public AddsongCommand(MusicPlaylists playlist) {
        this.musicPlaylists = playlist;
    }

    @Override
    public void execute(List<String> args) {
        List<Song> songs = musicPlaylists.getSongs();
        if (args.isEmpty() || args.size() < 3 || args.size() > 6) {
            System.out.println("Invalid arguments");
            return;
        }

        for (Song song : songs) {
            if (song.getTitle().equals(args.get(0)) && song.getArtist().equals(args.get(1))) {
                System.out.println("Song already exists");
                return;
            }
        }

        int songID;
        if (songs.isEmpty()) {
            songID = 1;
        } else {
            songID = songs.getLast().getID()+1;
        }

        Song newSong = new Song(songID, args.get(0), args.get(1), args.get(2));

        switch (args.size()) {
            case 6:
                newSong.setGenre(args.get(5));
            case 5:
                newSong.setYear(args.get(4));
            case 4:
                newSong.setAlbum(args.get(3));
                break;
        }

        songs.add(newSong);
    }
}
