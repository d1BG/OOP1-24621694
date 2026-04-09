package bg.tu_varna.sit.commands.genericCommands;

import bg.tu_varna.sit.commands.Command;
import bg.tu_varna.sit.commands.CommandsIndex;
import bg.tu_varna.sit.exceptions.CommandException;
import bg.tu_varna.sit.util.ArgumentParser;

import java.util.List;
import java.util.Map;

public class HelpCommand extends Command {
    private Map<CommandsIndex, Command> commands;

    public HelpCommand(Map<CommandsIndex, Command> commands) {
        this.commands = commands;
    }

    @Override
    public String execute(List<String> args) {
        ArgumentParser.argSizeChecker(args, 0, 1);

        String helpMessage = """
            All Available Commands:
                open <file>
                    Отваря файл със записани данни.
            
                close
                    Затваря текущия файл.
            
                save
                    Записва промените в текущия файл.
            
                saveas <file>
                    Записва данните в нов файл.
            
                help [<command name>]
                    Изважда това съобщение, ако има аргумент показва информация за специфичната команда.
            
                exit
                    Прекратява програмата.
            
                addsong <title> <artist> <duration> [<album>] [<year>] [<genre>]
                    Добавя нова песен. Ако съществува песен със същото <title> и <artist>, връща грешка.
            
                removesong <songId>
                    Изтрива песен по ID.
            
                listsongs [artist=<artist>] [genre=<genre>] [year=<year>]
                    Извежда списък с песни с възможност за филтриране.
            
                songinfo <songId>
                    Показва подробна информация за песен.
            
                createplaylist <name> [<description>]
                    Създава нов плейлист с уникално име.
            
                deleteplaylist <name>
                    Изтрива плейлист.
            
                addtoplaylist <playlistName> <songId> [pos=<n>]
                    Добавя песен към плейлист. Ако е зададена позиция – добавя я там.
            
                removefromplaylist <playlistName> <songId>
                    Премахва песен от плейлист.
            
                move <playlistName> <fromPos> <toPos>
                    Премества песен в рамките на плейлист.
            
                showplaylist <playlistName>
                    Извежда съдържанието на плейлиста и общата му продължителност.
            
                shuffle <playlistName> [seed=<n>]
                    Разбърква песните в плейлист.
            
                play <songId> [playlist=<playlistName>]
                    Отбелязва слушане на песен (записва дата и час).
            
                plays [from=<date>] [to=<date>] [playlist=<name>] [song=<songId>]
                    Справка за слушанията според зададени критерии.
            
                toptracks <n> [from=<date>] [to=<date>]
                    Извежда топ n най-слушани песни за даден период.
            
                topplaylists <n> [from=<date>] [to=<date>]
                    Извежда топ n най-активни плейлисти.
            
                topartists <n> [from=<date>] [to=<date>]
                    Извежда топ n изпълнители по слушания.
            
                lowactivity <from> <to> <thresholdPercent>
                    Извежда плейлисти с активност под зададения процент за периода.
            
                dropplaylist <playlistName>
                    Премахва плейлист от системата.
            
            Additional Commands:
                addartist <username> [<firstName>] [<lastName>]
                    Добавя артист. Уникален по псевдоним.

                listartists
                    Извежда всички артисти.

                removeartist <username>
                    Премахва артист по псевдоним.

            Debug commands:
                listplaylists
                    Изкарва имената на всички плейлисти.
            """;
        if (args.isEmpty()) {
            return helpMessage;
        }

        Command command = commands.get(CommandsIndex.fromName(args.getFirst()));
        if (command != null) {
            return command.cmdHelpMessage();
        }
        throw new CommandException("Unknown command: " + args.getFirst());
    }

    @Override
    public String cmdHelpMessage() {
        return "Изважда съобщение за помощ, ако има аргумент показва информация за специфичната команда.\n" +
                "   Usage: help [<command name>]";
    }
}
