package bg.tu_varna.sit.exceptions;

public class PlaylistException extends RuntimeException {
    /**
     * Грешка при грешна опотребаа на методи свързани с плейлисти
     * @param message съобщение за грешка
     */
    public PlaylistException(String message) {
        super(message);
    }
}
