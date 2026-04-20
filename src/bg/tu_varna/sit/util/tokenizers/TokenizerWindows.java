package bg.tu_varna.sit.util.tokenizers;

import java.util.ArrayList;
import java.util.List;

public class TokenizerWindows implements Tokenizer {
    private List<String> tokens = new ArrayList<>();
    public TokenizerWindows() {}

    /**
     * Метод за разделяна на потребителски вход за Windows. Разделя при интервал.
     * При отваряне на кавички оставя в същия аргумент, дори и при
     * използване на интервали, докато кавичките не биват затворени и не се
     * използва интервал.
     * @param line вход от потребителя
     * @return Връща листа от разделение аргументи
     */
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

    /**
     * Изчиства листата с токени (аргументи)
     */
    @Override
    public void clear() {
        tokens.clear();
    }
}
