package bg.tu_varna.sit.util.tokenizers;

import java.util.ArrayList;
import java.util.List;

/**
 * Имплементация на {@code Tokenizer} специфична за Linux, използва се и при всяка операциона
 * система която не е Windows. Използва специфики който UNIX-like операционните системи
 * използваат в своя {@code shell}, като например {@code escape} символ ({@code \})
 */
public class TokenizerLinux implements Tokenizer {
    /**
     * Съжържа разделения вход от потребителя, не се изтрива докато не се извика {@code clear()}
     */
    private List<String> tokens = new ArrayList<>();

    /**
     * Конструктор на имплементация на {@code Tokenizer} за Unix-like операционните система.
     */
    public TokenizerLinux() {}

    /**
     * Метод за разделяна на потребителски вход за Linux/MacOS/Unix-like системи.
     * Използва {@code \} като escape символ.
     * Разделя при интервал. При отваряне на кавички оставя
     * в същия аргумент, дори и при използване на интервали,
     * докато кавичките не биват затворени и не се използва интервал.
     * @param line вход от потребителя
     * @return Връща листа от разделение аргументи
     */
    @Override
    public List<String> tokenize(String line) {
        StringBuilder currToken = new StringBuilder();
        boolean inQuotes = false;
        boolean isEscaped = false;

        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '\\':
                    if  (inQuotes) {
                        currToken.append(line.charAt(i));
                        break;
                    }
                    if (isEscaped) {
                        currToken.append(line.charAt(i));
                        isEscaped = false;
                        break;
                    }
                    isEscaped = true;
                    break;
                case '"':
                    if (isEscaped) {
                        currToken.append(line.charAt(i));
                        isEscaped = false;
                        break;
                    }
                    inQuotes = !inQuotes;
                    break;
                case ' ':
                    if (isEscaped) {
                        currToken.append(line.charAt(i));
                        isEscaped = false;
                        break;
                    } else if (inQuotes) {
                        currToken.append(line.charAt(i));
                    } else if (!currToken.isEmpty()) {
                        tokens.add(currToken.toString());
                        currToken = new StringBuilder();
                    }
                    break;
                default:
                    if (isEscaped) {
                        currToken.append(line.charAt(i));
                        isEscaped = false;
                        break;
                    }
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
