package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.exceptions.CommandException;

import java.util.List;

public abstract class Command {
    /**
     * Изъплняващ метод на командата
     * @param args аргументи които командата приема
     * @return Резултат от командата
     * @throws CommandException за грешно подадени аргументи
     */
    protected abstract String execute(List<String> args) throws CommandException;

    /**
     * @return Съобщение за помощ
     */
    public abstract String cmdHelpMessage();
}
