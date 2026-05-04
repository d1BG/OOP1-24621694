package bg.tu_varna.sit.commands.genericCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;

/**
 * Клас за команда излизаща от програмата
 */
public class ExitCommand extends Command {

    /**
     * Конструктор за команда излизаща от програмата
     */
    public ExitCommand() {}

    /**
     * Изълняващ метод на команда за напускане на програмта
     * Командата връща "връща" {@code null}, но технически не стига до там.
     * Командата излиза от програмата преди да завъриши изълнението си.
     * @param args аргументи които командата приема
     * @return {@code null}
     */
    @Override
    protected String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args);

        System.exit(0);
        return null; // null since the program is exiting, there is no need to return anything.
    }

    public String cmdHelpMessage() {
        return "Прекратява програмата.\n" +
                "   Usage: exit";
    }
}
