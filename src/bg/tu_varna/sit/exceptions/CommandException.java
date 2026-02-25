package bg.tu_varna.sit.exceptions;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }
}
