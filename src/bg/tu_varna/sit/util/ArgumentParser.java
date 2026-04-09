package bg.tu_varna.sit.util;

import bg.tu_varna.sit.exceptions.CommandException;

import java.util.HashMap;
import java.util.List;
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

    public static Map<String, String> KeyValueParser(List<String> args) {
        Map<String, String> parsedKeyValue = new HashMap<>();
        for (String arg : args) {
            String[] split = arg.trim().split("=");
            if (split.length != 2) {
                throw new CommandException("Couldn't Parse: " + arg);
            }
            parsedKeyValue.put(split[0], split[1]);
        }
        return parsedKeyValue;
    }

    // Checker for argument size
        // with min and max
    public static void argSizeChecker(List<String> args, int min, int max) {
        if (args.size() < min || args.size() > max) {
            throw new CommandException("Invalid Arguments: provided " + args.size() +
                    ", command asks for " + min + " minimum and " + max + " maximum."
            );
        }
    }
        // with a hard-set limit
    public static void argSizeChecker(List<String> args, int eq) {
        if (args.size() != eq) {
            throw new CommandException(
                    "Invalid Arguments: provided " + args.size() + ", needed " + eq
            );
        }
    }
        // check for no args
    public static void argSizeChecker(List<String> args) {
        if (!args.isEmpty()) {
            throw new CommandException(
                    "Invalid Arguments: Command doesnt take any arguments"
            );
        }
    }
}
