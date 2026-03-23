package bg.tu_varna.sit.util;

import bg.tu_varna.sit.exceptions.TimeFormatException;

import java.time.LocalDateTime;

public class DateTimeParser {
    // parses dd.MM.yyyy-hh:mm to ISO-8601

    public static LocalDateTime parse(String dateTime) {
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
}