package bg.tu_varna.sit.exceptions;

public class SongException extends RuntimeException {
    /**
     * Грешка при грешна опотребаа на методи свързани с песни
     * @param message съобщение за грешка
     */
    public SongException(String message) {
        super(message);
    }
}
