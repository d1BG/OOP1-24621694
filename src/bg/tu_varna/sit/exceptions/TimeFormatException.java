package bg.tu_varna.sit.exceptions;

public class TimeFormatException extends RuntimeException {
    /**
     * Грешка при грешна опотребаа на методи свързани с форматиране на време/дати
     * @param message съобщение за грешка
     */
    public TimeFormatException(String message) {
        super(message);
    }
}
