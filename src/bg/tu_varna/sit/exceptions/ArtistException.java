package bg.tu_varna.sit.exceptions;

public class ArtistException extends RuntimeException {
    /**
     * Грешка при грешна опотребаа на методи свързани с Артист модела
     * @param message съобщение за грешка
     */
    public ArtistException(String message) {
        super(message);
    }
}
