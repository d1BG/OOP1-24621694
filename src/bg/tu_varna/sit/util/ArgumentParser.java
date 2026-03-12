package bg.tu_varna.sit.util;

import java.util.Map;

public class ArgumentParser {
    // Parser for key=value type of arguments
    public static Map<String, String> KeyValueParser(String arg) {
        String[] split = arg.trim().split("=");
        return Map.of(split[0], split[1]);
    }
}
