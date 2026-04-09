package bg.tu_varna.sit.commands.playlistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlaylistActions;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.models.Song;
import bg.tu_varna.sit.models.TimeDuration;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

public class ShowPlaylistCommand implements Command {
    private PlaylistActions playlistActions;
    public ShowPlaylistCommand(PlaylistActions playlistActions) {
        this.playlistActions = playlistActions;
    }

    @Override
    public String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1);

        Playlist pl = playlistActions.getPlaylistByName(args.getFirst());
        StringBuilder sb = new StringBuilder();

        TimeDuration playlistDuration = new TimeDuration();
        for (Song s : pl.getSongs()) {
            sb.append(s.toString()).append("\n");
            playlistDuration.addDurations(s.getDuration());
        }

        sb.append("\nTotal Playlist Duration: ").append(playlistDuration);

        return sb.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда съдържанието на плейлиста и общата му продължителност.\n" +
                "   Usage: showplaylist <playlistName>";
    }
}
