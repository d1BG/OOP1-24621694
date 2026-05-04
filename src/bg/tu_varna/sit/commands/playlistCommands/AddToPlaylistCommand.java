package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;
import java.util.Map;

/**
 * Клас за добавяне на песен в плейлисти
 */
public class AddToPlaylistCommand extends Command {
    /**
     * Мениджър за музикални плейлисти
     */
    private MusicPlaylists musicPlaylists;

    /**
     * Конструтор на команда за добавяне на песен в плейлист
     * @param musicPlaylists Мениджър за музикални плейлисти
     */
    public AddToPlaylistCommand(MusicPlaylists musicPlaylists) {
        this.musicPlaylists = musicPlaylists;
    }

    /**
     * Изълняващ метод на команда за добавяне на песен в плейлист
     * @param args аргументи които командата приема
     * @return съобщение за успех
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 2, 3);

        Integer pos = null;
        try {
            if (args.size() == 3) {
                Map<String, String> optPos = ArgumentParser.KeyValueParser(args.get(2));
                pos = Integer.parseInt(optPos.get("pos"));
            }
        } catch (NumberFormatException e) {
            throw new CommandException("Position must be a number");
        }

        musicPlaylists.addSongToPlaylist(
                musicPlaylists.getPlaylistActions().getPlaylistByName(args.get(0)),
                musicPlaylists.getSongActions().getSongById(Integer.parseInt(args.get(1))),
                pos
        );


        return "Successfully added song to playlist";
    }

    @Override
    public String cmdHelpMessage() {
        return "Добавя песен към плейлист. Ако е зададена позиция – добавя я там.\n" +
                "   Usage: addtoplaylist <playlistName> <songId> [pos=<n>]";
    }
}
