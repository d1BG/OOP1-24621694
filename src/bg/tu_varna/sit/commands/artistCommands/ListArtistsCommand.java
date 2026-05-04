package bg.tu_varna.sit.commands.artistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда извеждаща всички артисти
 */
public class ListArtistsCommand extends Command {
    /**
     * Мениджър за артисти
     */
    private ArtistActions artistActions;

    /**
     * Конструктор на команда за извеждане на всички артисти
     * @param artistActions мениджър за артисти
     */
    public ListArtistsCommand(ArtistActions artistActions) {
        this.artistActions = artistActions;
    }

    /**
     * Изълняващ метод на команда за извеждане на списък с всички артисти
     * @param args аргументи които командата приема
     * @return всички артисти
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args);

        StringBuilder sb = new StringBuilder();
        for (Artist a : artistActions.getArtists()) {
            sb.append(a).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String cmdHelpMessage() {
        return "Извежда всички артисти.\n" +
                "   Usage: listartists";
    }
}
