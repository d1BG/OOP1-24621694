package bg.tu_varna.sit.util;

import java.util.ArrayList;
import java.util.List;

public class TokenizerWindows implements Tokenizer {
    List<String> tokens = new ArrayList<>();
    public TokenizerWindows() {}

    @Override
    public List<String> tokenize(String line) {
        StringBuilder currToken = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '"':
                    inQuotes = !inQuotes;
                    break;
                case ' ':
                    if (inQuotes) {
                        currToken.append(line.charAt(i));
                    } else if (!currToken.isEmpty()) {
                        tokens.add(currToken.toString());
                        currToken = new StringBuilder();
                    }
                    break;
                default:
                    currToken.append(line.charAt(i));
            }
        }
        if (!currToken.isEmpty()) {
            tokens.add(currToken.toString()); // add the last token
        }

        return tokens;
    }

    @Override
    public void clear() {
        tokens.clear();
    }
}
