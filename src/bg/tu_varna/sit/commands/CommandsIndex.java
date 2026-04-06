package bg.tu_varna.sit.commands;

public enum CommandsIndex {
    EXIT("exit"),
    HELP("help"),
    CREATE_PLAYLIST("createplaylist"),
    LIST_PLAYLISTS("listplaylists"),
    DELETE_PLAYLIST("deleteplaylist"),
    ADD_SONG("addsong"),
    LIST_SONGS("listsongs"),
    SONG_INFO("songinfo"),
    REMOVE_SONG("removesong"),
    SAVE_AS("saveas"),
    SAVE("save"),
    OPEN("open"),
    CLOSE("close"),
    ADD_TO_PLAYLIST("addtoplaylist"),
    REMOVE_FROM_PLAYLIST("removefromplaylist"),
    PLAY("play"),
    PLAYS("plays"),
    SHOW_PLAYLIST("showplaylist"),
    DROP_PLAYLIST("dropplaylist"),
    ADD_ARTIST("addartist"),
    LIST_ARTISTS("listartists"),
    REMOVE_ARTIST("removeartist"),
    TOP_ARTISTS("topartists"),
    TOP_TRACKS("toptracks"),
    TOP_PLAYLISTS("topplaylists"),
    UNKNOWN_COMMAND(null);

    private final String name;

    CommandsIndex(String name){
        this.name = name;
    };

    public String getName() {
        return name;
    }

    public static CommandsIndex fromName(String name) {
        return switch (name.toLowerCase()) {
            case "exit" -> EXIT;
            case "help" -> HELP;
            case "createplaylist" -> CREATE_PLAYLIST;
            case "listplaylists" -> LIST_PLAYLISTS;
            case "deleteplaylist" -> DELETE_PLAYLIST;
            case "addsong" -> ADD_SONG;
            case "listsongs" -> LIST_SONGS;
            case "songinfo" -> SONG_INFO;
            case "removesong" -> REMOVE_SONG;
            case "saveas" -> SAVE_AS;
            case "save" -> SAVE;
            case "open" -> OPEN;
            case "close" -> CLOSE;
            case "addtoplaylist" -> ADD_TO_PLAYLIST;
            case "removefromplaylist" -> REMOVE_FROM_PLAYLIST;
            case "play" -> PLAY;
            case "plays" -> PLAYS;
            case "showplaylist" -> SHOW_PLAYLIST;
            case "dropplaylist" -> DROP_PLAYLIST;
            case "addartist" -> ADD_ARTIST;
            case "listartists" -> LIST_ARTISTS;
            case "removeartist" -> REMOVE_ARTIST;
            case "topartists" -> TOP_ARTISTS;
            case "toptracks" -> TOP_TRACKS;
            case "topplaylists" -> TOP_PLAYLISTS;
            default -> UNKNOWN_COMMAND;
        };
    }

}
