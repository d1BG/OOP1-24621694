package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.data.interfaces.ArtistActions;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.models.Artist;

import java.util.List;

public class ListArtistsCommand implements Command{
    ArtistActions artistActions;
    public ListArtistsCommand(ArtistActions artistActions) {
        this.artistActions = artistActions;
    }

    @Override
    public String execute(List<String> args) {
        if (!args.isEmpty()) {
            throw new CommandException("Invalid Arguments");
        }
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
