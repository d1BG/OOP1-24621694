package bg.tu_varna.sit.util.tokenizers;

import java.util.List;

/**
 * Интерфейс дефиниращ метод за разделяна на потребителски вход на части (токени/аргументи)
 */
public interface Tokenizer {
    List<String> tokenize(String line);

    /**
     * Изчиства листата с токени (аргументи)
     */
    void clear();
}
