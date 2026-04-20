package bg.tu_varna.sit.commands.playHistoryCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.PlayHistoryActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Playlist;
import bg.tu_varna.sit.util.ArgumentParser;
import bg.tu_varna.sit.util.DateTimeParser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class LowActivityCommand extends Command {
    private PlayHistoryActions playHistoryActions;
    public LowActivityCommand(PlayHistoryActions playHistoryActions) {
        this.playHistoryActions = playHistoryActions;
    }

    /**
     * Изълняващ метод на команда за извеждане на плейлисти с слушаност под подаден процент за определен период
     * @param args аргументи които командата приема
     * @return Листа с плейлисти с по-ниска слушаност от подадената за подаден период
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 3);
        LocalDateTime fromFilter = DateTimeParser.parse(args.get(0));
        LocalDateTime toFilter = DateTimeParser.parse(args.get(1));
        int threshold;
        try {
            threshold = Integer.parseInt(args.get(2));
        } catch (NumberFormatException e) {
            throw new CommandException("thresholdPercent must be a number");
        }

        Map<Playlist, Double> playlistActivity = playHistoryActions.lowActivity(fromFilter, toFilter, threshold);
        StringBuilder sb = new StringBuilder("Playlists with activity under ").append(threshold).append("%\n");
        playlistActivity.forEach((p, v) -> {
            sb.append(p.getName()).append(" - ").append(v.intValue()).append("%\n");
        });

        return sb.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда плейлисти с активност под зададения процент за периода.\n" +
                "(Не включва слушания без зададен плейлист в изчислението)\n" +
                "   Usage: lowactivity <from> <to> <thresholdPercent>";
    }
}
