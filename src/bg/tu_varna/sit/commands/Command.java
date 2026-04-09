package bg.tu_varna.sit.commands;

import java.util.List;

public abstract class Command {
    protected abstract String execute(List<String> args);
    public abstract String cmdHelpMessage();
}
