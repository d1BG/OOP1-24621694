package bg.tu_varna.sit.commands.artistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.models.Artist;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда създаваща артист
 */
public class AddArtistCommand extends Command {
    /**
     * Мениджър за артисти
     */
    private ArtistActions artistActions;

    /**
     * Конструктор на команда за създаване на артист
     * @param artistActions мениджър за артисти
     */
    public AddArtistCommand(ArtistActions artistActions) {
        this.artistActions = artistActions;
    }

    /**
     * Изълняващ метод на команда за добавяне на артист
     * @param args аргументи които командата приема
     * @return съобщение за успех с псевдонима на добавеня
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 1, 3);

        String firstName = null;
        String lastName = null;

        switch (args.size()) {
            case 3:
                lastName = args.get(2);
            case 2:
                firstName = args.get(1);
            case 1:
                Artist newArtist = new Artist(args.getFirst());
                newArtist.setFirstName(firstName);
                newArtist.setLastName(lastName);
                artistActions.addArtist(newArtist);
            break;
        }
        return "Successfully added artist: " + args.getFirst();
    }

    @Override
    public String cmdHelpMessage() {
        return "Добавя артист. Уникален по псевдоним.\n" +
                " Usage: addartist <username> [<firstName>] [<lastName>]";
    }
}
