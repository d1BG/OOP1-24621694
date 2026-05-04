package bg.tu_varna.sit.commands.genericCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Random;

/**
 * Клас за команда генрираща артисти, плейлисти и песни
 */
public class GenerateCommand extends Command {
    /**
     * Мениджър за музикални плейлисти
     */
    private MusicPlaylists musicPlaylists;

    /**
     * Конструктор на команда генрираща информация
     * @param musicPlaylists Мениджър за музикални плейлисти
     */
    public GenerateCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за генериране на голямо количесто песни/артисти/пускания
     * Генерираните елементи са в периода 1-ви Януари до 31-ви Март
     * @param args аргументи които командата приема
     * @return Съобщение за успех
     */
    @Override
    protected String execute(List<String> args) {
        Random rand = new Random();

        for (int i = 0; i < Integer.parseInt(args.get(0)); i++) {
            musicPlaylists.getPlaylistActions().createPlaylist(new Playlist("playlist" + (i+1)));
        }

        for (int i = 0; i < Integer.parseInt(args.get(1)); i++) {
            musicPlaylists.getArtistActions().addArtist(new Artist("artist" + (i+1)));
        }

        for (Artist a : musicPlaylists.getArtistActions().getArtists()) {
            for (int i = 0; i < Integer.parseInt(args.get(2)); i++) {
                musicPlaylists.getSongActions().addSong(
                        new Song(musicPlaylists.getSongActions().getNextSongID(),
                                "song"+i,
                                a,
                                TimeDuration.genRandDuration(),
                                null,
                                null,
                                Genre.NA));
            }
        }

        for (Playlist p : musicPlaylists.getPlaylistActions().getPlaylists()) {
            for (int i = 0; i < rand.nextInt(3, musicPlaylists.getSongActions().getSongs().size()/2); i++) {
                musicPlaylists.addSongToPlaylist(p,
                        musicPlaylists.getSongActions().getSongById( rand.nextInt(1, musicPlaylists.getSongActions().getSongs().size())),
                        null);
            }

            for (int i = 0; i < rand.nextInt(Integer.parseInt(args.get(3))); i++) {
                LocalDate ld = LocalDate.of(2026, Month.of(rand.nextInt(1,4)), rand.nextInt(1,29));
                LocalTime lt = LocalTime.now();
                musicPlaylists.getPlayHistoryActions().play(
                        p.getSongs().get(rand.nextInt(p.getSongs().size())),
                        p,
                        LocalDateTime.of(ld, lt)
                );
            }
        }



        return "Generated Data";
    }

    @Override
    public String cmdHelpMessage() {
        return """
                Генерира артисти, плейлисти, песни и слушания
                maxPlaysRand is used in a randomizer between it and 0 for plays from a playlist
                   Usage: gen <playlistCount> <artistsCount> <songsPerArtist> <maxPlaysRand>
                """;
    }
}
