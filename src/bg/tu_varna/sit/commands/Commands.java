package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.commands.artistCommands.*;
import bg.tu_varna.sit.commands.fileCommands.*;
import bg.tu_varna.sit.commands.genericCommands.*;
import bg.tu_varna.sit.commands.playHistoryCommands.*;
import bg.tu_varna.sit.commands.playHistoryCommands.topCommands.*;
import bg.tu_varna.sit.commands.playlistCommands.*;
import bg.tu_varna.sit.commands.songCommands.*;
import bg.tu_varna.sit.data.fileServices.FileActions;
import bg.tu_varna.sit.data.interfaces.MusicPlaylists;
import bg.tu_varna.sit.exceptions.CommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Клас изпълняващ команди
 */
public class Commands {
    /**
     * Map съдържащ всички командите и начин за търсенето им с скорост O(n^0)
     */
    private final Map<CommandsIndex, Command> commands = new HashMap<>();

    public Commands(MusicPlaylists musicPlaylists, FileActions fileActions) {
        commands.put(CommandsIndex.EXIT, new ExitCommand());
        commands.put(CommandsIndex.HELP, new HelpCommand(commands));
        commands.put(CommandsIndex.CREATE_PLAYLIST, new CreatePlaylistCommand(musicPlaylists.getPlaylistActions()));
        commands.put(CommandsIndex.LIST_PLAYLISTS, new ListPlaylistsCommand(musicPlaylists.getPlaylistActions()));
        commands.put(CommandsIndex.DELETE_PLAYLIST, new DeletePlaylistCommand(musicPlaylists.getPlaylistActions()));
        commands.put(CommandsIndex.ADD_SONG, new AddSongCommand(musicPlaylists.getSongActions(), musicPlaylists.getArtistActions()));
        commands.put(CommandsIndex.LIST_SONGS, new ListSongsCommand(musicPlaylists.getSongActions(), musicPlaylists.getArtistActions()));
        commands.put(CommandsIndex.SONG_INFO, new SongInfoCommand(musicPlaylists.getSongActions()));
        commands.put(CommandsIndex.REMOVE_SONG, new RemoveSongCommand(musicPlaylists.getSongActions()));
        commands.put(CommandsIndex.SAVE_AS, new SaveAsCommand(musicPlaylists, fileActions));
        commands.put(CommandsIndex.SAVE, new SaveCommand(musicPlaylists, fileActions));
        commands.put(CommandsIndex.OPEN, new OpenCommand(musicPlaylists, fileActions));
        commands.put(CommandsIndex.CLOSE, new CloseCommand(musicPlaylists, fileActions));
        commands.put(CommandsIndex.ADD_TO_PLAYLIST, new AddToPlaylistCommand(musicPlaylists));
        commands.put(CommandsIndex.REMOVE_FROM_PLAYLIST, new RemoveFromPlaylistCommand(musicPlaylists));
        commands.put(CommandsIndex.PLAY, new PlayCommand(musicPlaylists));
        commands.put(CommandsIndex.PLAYS, new PlaysCommand(musicPlaylists));
        commands.put(CommandsIndex.SHOW_PLAYLIST, new ShowPlaylistCommand(musicPlaylists.getPlaylistActions()));
        commands.put(CommandsIndex.DROP_PLAYLIST, new DropPlaylistCommand(musicPlaylists));
        commands.put(CommandsIndex.ADD_ARTIST, new AddArtistCommand(musicPlaylists.getArtistActions()));
        commands.put(CommandsIndex.LIST_ARTISTS, new ListArtistsCommand(musicPlaylists.getArtistActions()));
        commands.put(CommandsIndex.REMOVE_ARTIST, new RemoveArtistCommand(musicPlaylists));
        commands.put(CommandsIndex.TOP_ARTISTS, new TopArtistsCommand(musicPlaylists.getPlayHistoryActions()));
        commands.put(CommandsIndex.TOP_TRACKS, new TopTracksCommand(musicPlaylists.getPlayHistoryActions()));
        commands.put(CommandsIndex.TOP_PLAYLISTS, new TopPlaylistsCommand(musicPlaylists.getPlayHistoryActions()));
        commands.put(CommandsIndex.LOW_ACTIVITY, new LowActivityCommand(musicPlaylists.getPlayHistoryActions()));
        commands.put(CommandsIndex.SHUFFLE, new ShuffleCommand(musicPlaylists.getPlaylistActions()));
        commands.put(CommandsIndex.MOVE, new MoveCommand(musicPlaylists.getPlaylistActions()));
        commands.put(CommandsIndex.GEN, new GenerateCommand(musicPlaylists));
    }

    /**
     * Намира командата която потребителя иска да изълни и я изълнява подавайки аргументитие които са били въведени
     * @param cmdName името на командата за която се търси
     * @param args аргументите които камандата приема
     * @return резултата след извършване на командата
     * @throws CommandException ако командата не е намерена
     */
    public String exec(String cmdName, List<String> args) throws CommandException {
        Command cmd = commands.get(CommandsIndex.fromName(cmdName));
        if (cmd != null) {
            return cmd.execute(args);
        } else {
            throw new CommandException("Unknown command: " + cmdName);
        }
    }
}