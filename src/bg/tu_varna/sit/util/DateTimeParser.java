package bg.tu_varna.sit.util;

import bg.tu_varna.sit.exceptions.TimeFormatException;

import java.time.LocalDateTime;
// TODO: nuke this, use LocalDateTime.format()

/**
 * {@code Parser} на дати от формат {@code dd.MM.yyyy-hh:mm} към {@code ISO-8601} (подходящ за {@code LocalDateTime})
 * и форматър за обратната операция.
 */
public class DateTimeParser {

    /**
     * Parser на дати и часове от {@code dd.MM.yyyy-hh:mm} към {@code ISO-8601} ({@code LocalDateTime})
     * @param dateTime String в формат {@code dd.MM.yyyy-hh:mm}
     * @return обект от тип {@code LocalDateTime}
     * @throws TimeFormatException ако подадения низ не е в правилния формат или са подадени невалидни данни
     */
    public static LocalDateTime parse(String dateTime) throws TimeFormatException {
        try {
            String[] splitDateTime = dateTime.split("-");
            String[] date = splitDateTime[0].split("\\.");
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);

            String[] time = splitDateTime[1].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new TimeFormatException("Format must be: dd.MM.yyyy-hh:mm");
        }
    }

    /**
     * Форматър на {@code LocalDateTime} обекти
     * @param dateTime време което да се превърне в {@code String}
     * @return {@code String} в формат {@code dd.MM.yyyy-hh:mm}
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.getDayOfMonth() + "." +
                dateTime.getMonth().getValue() + "." +
                dateTime.getYear() + "-" +
                dateTime.getHour() + ":" +
                dateTime.getMinute();
    }
}