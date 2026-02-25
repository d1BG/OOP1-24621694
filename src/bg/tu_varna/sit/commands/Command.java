package bg.tu_varna.sit.commands;

import java.util.List;

public interface Command {
    void execute(List<String> args);
    String cmdHelpMessage();
}
