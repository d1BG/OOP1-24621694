package bg.tu_varna.sit;

import java.util.ArrayList;

public class CmdLineParser {
    public CmdLineParser(ArrayList<String> tokens, MusicPlaylists musicPlaylists) {
        switch(tokens.getFirst()) {
            case "open":
            case "close":
            case "save":
            case "saveas":
            case "addtoplaylist":
            case "removefromplaylist":
            case "move":
            case "showplaylist":
            case "shuffle":
            case "play":
            case "plays":
            case "toptracks":
            case "topplaylists":
            case "topartists":
            case "lowactivity":
            case "dropplaylist":
                System.out.println("Command not implemented yet");
                break;
            case "songinfo":
                musicPlaylists.songInfo(Integer.parseInt(tokens.get(1)));
                break;
            case "removesong":
                musicPlaylists.removeSong(Integer.parseInt(tokens.get(1)));
                break;
            case "listsongs":
                musicPlaylists.listSongs();
                break;
            case "addsong":
                switch(tokens.size()) {
                    case 1:
                    case 2:
                    case 3:
                        System.out.println("Command usage: addsong <title> <artist> <duration> [<album>] [<year>] [<genre>]");
                        return;
                    case 4:
                        musicPlaylists.addSong(tokens.get(1), tokens.get(2), tokens.get(3), "", "", "");
                        break;
                    case 5:
                        musicPlaylists.addSong(tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4), "", "");
                        break;
                    case 6:
                        musicPlaylists.addSong(tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4), tokens.get(5), "");
                        break;
                    case 7:
                        musicPlaylists.addSong(tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4), tokens.get(5), tokens.get(6));
                        break;
                    default:
                        System.out.println("Too many arguments");
                }
                break;
            case "listplaylists":
                musicPlaylists.listPlaylists();
                break;
            case "createplaylist":
                if (tokens.size() == 2) {
                    musicPlaylists.createPlaylist(tokens.get(1));
                    break;
                } else if (tokens.size() == 3) {
                    musicPlaylists.createPlaylist(tokens.get(1), tokens.get(2));
                    break;
                }
                System.out.println("Invalid usage of the command, do 'help' for usage");
                break;
            case "deleteplaylist":
                musicPlaylists.deletePlaylist(tokens.get(1));
                break;
            case "help":
                printHelp();
                break;
            case "exit":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Command " + tokens.getFirst() + " not found, do 'help' for available commands");
        }
    }

    public void printHelp() {
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
            
                help
                    Изважда това съобщение.
            
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
            
            Debug commands:
                listplaylists
                    Изкарва имената на всички плейлисти.
            """;
        System.out.print(helpMessage);
    }
}
