package bg.tu_varna.sit.models;

import bg.tu_varna.sit.exceptions.TimeFormatException;

import java.io.Serializable;
import java.util.Random;

public class TimeDuration implements Serializable {
    private int hours;
    private int minutes;
    private int seconds;

    /**
     * Конструктор за обект запазващ дължината на песен.
     * @param duration {@code String} в формат {@code HH:MM:SS} ('{@code HH:}' - часовете, не са задължителни).
     * @throws TimeFormatException ако формата на низа е невалиден
     */
    public TimeDuration(String duration) throws TimeFormatException {
        String[] split = duration.split(":");
        try {
            for (String segment : split) {
                if (Integer.parseInt(segment) >= 60 || Integer.parseInt(segment) < 0) {
                    throw new TimeFormatException("Invalid duration format");
                }
            }

            if (split.length == 2) {
                minutes = Integer.parseInt(split[0]);
                seconds = Integer.parseInt(split[1]);
            }

            if (split.length == 3) {
                hours = Integer.parseInt(split[0]);
                minutes = Integer.parseInt(split[1]);
                seconds = Integer.parseInt(split[2]);
            }
        } catch (NumberFormatException e) {
            throw new TimeFormatException("Invalid duration format");
        }
    }

    /**
     * Конструктор за обект запазващ дължината на песен.
     * Инициализира дължината към 0.
     */
    public TimeDuration() {
        hours = minutes = seconds = 0;
    }

    /**
     * Добавя на две дължини.
     * @param duration дължината която се добавя към този обект.
     */
    public void addDurations(TimeDuration duration) {
        seconds += duration.seconds;
        validateSeconds();

        minutes += duration.minutes;
        validateMinutes();

        hours += duration.hours;
    }

    /**
     * Валидира на секундите на този обект,
     * използва се при добавяне наа дължини
     */
    private void validateSeconds() {
        if (seconds >= 60) {
            seconds -= 60;
            minutes++;
            validateMinutes();
        }
    }

    /**
     * Валидира на минутите на този обект,
     * използва се при добавяне наа дължини
     */
    private void validateMinutes() {
        if (minutes >= 60) {
            minutes -= 60;
            hours++;
        }
    }

    /**
     * toString метод форматиращ дължината в песен за четене, интуитивен формат.
     * @return {@code String} в формат {@code HH:MM:SS} - където чава не се изписва ако е 0.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(hours).append(":");
        }
        sb.append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));
        return sb.toString();
    }

    /**
     * Констуктор за обект съхраняващ дължината на песен изполван единствено за {@code genRandDuration()}
     * @param mins число за минути
     * @param secs число за секунди
     */
    private TimeDuration(int mins, int secs) {
        this.minutes = mins;
        this.seconds = secs;
    }

    /**
     * Метод за генериране на произволна дължина.
     * @return Обект за съхранение на дължина на песен с произволни данни.
     */
    public static TimeDuration genRandDuration() {
        Random rand = new Random();
        return new TimeDuration(rand.nextInt(5), rand.nextInt(60));
    }
}
