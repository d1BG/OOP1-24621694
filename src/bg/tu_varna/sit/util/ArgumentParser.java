package bg.tu_varna.sit.util;

import bg.tu_varna.sit.exceptions.CommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentParser {
    /**
     * Parser на {@code String} в формат ключ=стойност към Map
     * @param arg {@code String} в формат ключ=стойност
     * @return Разделения arg сложен в Map
     * @throws CommandException ако някоя от подадената стойност са в грешния формат
     */
    public static Map<String, String> KeyValueParser(String arg) throws CommandException {
        String[] split = arg.trim().split("=");
        if (split.length != 2) {
            throw new CommandException("Couldn't Parse: " + arg);
        }
        return Map.of(split[0], split[1]);
    }

    /**
     * Parser на {@code List<String>} в формат ключ=стойност към Map
     * @param args {@code List<String>} в формат ключ=стойност
     * @return Разделените args сложени в Map
     * @throws CommandException ако някоя от подадените стойности са в грешния формат
     */
    public static Map<String, String> KeyValueParser(List<String> args) throws CommandException {
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

    /**
     * Метод за проверка на броя на аргументи
     * @param args подадените аргументи
     * @param min минимума аргументи изисквани
     * @param max максимума аргументи изисквани
     * @throws CommandException ако бъдат подадени повече от {@code max} и по малко от {@code min} на брой аргументи
     */
    public static void argSizeChecker(List<String> args, int min, int max) throws CommandException {
        if (args.size() < min || args.size() > max) {
            throw new CommandException("Invalid Arguments: provided " + args.size() +
                    ", command asks for " + min + " minimum and " + max + " maximum."
            );
        }
    }

    /**
     * Метод за проверка на броя на аргументи
     * @param args подадените аргументи
     * @param eq брой параметри които трябва да бъдат подадени
     * @throws CommandException ако подадените аргументи не са {@code eq} на брой
     */
    public static void argSizeChecker(List<String> args, int eq) throws CommandException {
        if (args.size() != eq) {
            throw new CommandException(
                    "Invalid Arguments: provided " + args.size() + ", needed " + eq
            );
        }
    }

    /**
     * Метод за проверка на броя на аргументи.
     * Ако аргументите не са празни, хвърля грешка.
     * @param args подадените аргументи
     * @throws CommandException ако бъдат подадени повече от 0 аргументи
     */
    public static void argSizeChecker(List<String> args) throws CommandException {
        if (!args.isEmpty()) {
            throw new CommandException(
                    "Invalid Arguments: Command doesnt take any arguments"
            );
        }
    }
}
