package bg.tu_varna.sit.models;

import bg.tu_varna.sit.exceptions.TimeFormatException;

public class TimeDuration {
    private int hours;
    private int minutes;
    private int seconds;

    TimeDuration(String duration) {
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

    public void addDurations(TimeDuration duration) {
        seconds += duration.seconds;
        validateSeconds();

        minutes += duration.minutes;
        validateMinutes();

        hours += duration.hours;
    }

    private void validateSeconds() {
        if (seconds >= 60) {
            seconds =- 60;
            minutes++;
            validateMinutes();
        }
    }

    private void validateMinutes() {
        if (minutes >= 60) {
            minutes =- 60;
            hours++;
        }
    }

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
}
