package bg.tu_varna.sit.util.tokenizers;

import java.util.List;

public interface Tokenizer {
    List<String> tokenize(String line);
    void clear();
}
