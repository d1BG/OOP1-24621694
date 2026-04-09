package bg.tu_varna.sit.commands.artistCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Artist;

import java.util.List;

public class AddArtistCommand implements Command {
    private ArtistActions artistActions;
    public AddArtistCommand(ArtistActions artistActions) {
        this.artistActions = artistActions;
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty() || args.size() > 3) {
            throw new CommandException("Invalid Arguments");
        }

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
