package bg.tu_varna.sit.commands;

import java.util.List;

public interface Command {
    String execute(List<String> args);
    String cmdHelpMessage();
}
