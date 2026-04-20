package bg.tu_varna.sit.exceptions;

public class CommandException extends RuntimeException {
    /**
     * Грешка при грешна опотребаа на методи свързани с изпълнението на команди
     * @param message съобщение за грешка
     */
    public CommandException(String message) {
        super(message);
    }
}
