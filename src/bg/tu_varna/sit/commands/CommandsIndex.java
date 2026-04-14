package bg.tu_varna.sit.commands;

import java.util.HashMap;
import java.util.Map;

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
    LOW_ACTIVITY("lowactivity"),
    GEN("gen"),
    UNKNOWN_COMMAND(null);

    private final String name;
    private static final Map<String, CommandsIndex> BY_NAME = new HashMap<>();

    CommandsIndex(String name){
        this.name = name;
    };

    public String getName() {
        return name;
    }

    static {
        for (CommandsIndex c : values()) {
            BY_NAME.put(c.name, c);
        }
    }

    public static CommandsIndex fromName(String name) {
        return BY_NAME.get(name.toLowerCase());
    }

}
