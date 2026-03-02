package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.SongActions;
import bg.tu_varna.sit.exceptions.SongException;
import bg.tu_varna.sit.models.Song;

import java.util.List;

public class AddsongCommand implements Command {
    private SongActions songActions;
    public AddsongCommand(SongActions songActions) {
        this.songActions = songActions;
    }

    @Override
    public String execute(List<String> args) {
        List<Song> songs = songActions.getSongs();
        if (args.isEmpty() || args.size() < 3 || args.size() > 6) {
            throw new SongException("Invalid arguments");
        }

        for (Song song : songs) {
            if (song.getTitle().equals(args.get(0)) && song.getArtist().equals(args.get(1))) {
                throw new SongException("Song already exists");
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

        songActions.addSong(newSong);
        return "Added new song with ID:" + songID;
    }

    @Override
    public String cmdHelpMessage() {
        return "Добавя нова песен. Ако съществува песен със същото <title> и <artist>, връща грешка.\n" +
                "   Usage: addsong <title> <artist> <duration> [<album>] [<year>] [<genre>]";
    }
}
