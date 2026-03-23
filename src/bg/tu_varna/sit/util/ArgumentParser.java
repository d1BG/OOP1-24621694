package bg.tu_varna.sit.util;

import bg.tu_varna.sit.exceptions.CommandException;

import java.util.Map;

public class ArgumentParser {
    // Parser for key=value type of arguments
    public static Map<String, String> KeyValueParser(String arg) {
        String[] split = arg.trim().split("=");
        if (split.length != 2) {
            throw new CommandException("Couldn't Parse: " + arg);
        }
        return Map.of(split[0], split[1]);
    }
}
