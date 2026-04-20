package bg.tu_varna.sit.exceptions;

public class FileException extends RuntimeException {
    /**
     * Грешка при грешки свързани с файлово
     * @param message съобщение за грешка
     */
    public FileException(String message) {
        super(message);
    }
}
