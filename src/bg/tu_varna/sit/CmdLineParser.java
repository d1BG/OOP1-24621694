package bg.tu_varna.sit;

public class CmdLineParser {
    public CmdLineParser(String[] cmdLine) {
        switch(cmdLine[0]) {
            case "open":
            case "close":
            case "save":
            case "saveas":
            case "help":
            case "addsong":
            case "removesong":
            case "listsongs":
            case "songinfo":
            case "createplaylist":
            case "deleteplaylist":
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
            case "exit":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Command " + cmdLine[0] + " not found, do 'help' for available commands");
        }
    }
}
