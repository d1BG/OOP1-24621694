package bg.tu_varna.sit;

import java.util.List;

public interface Command {
    void execute(List<String> args);
}
