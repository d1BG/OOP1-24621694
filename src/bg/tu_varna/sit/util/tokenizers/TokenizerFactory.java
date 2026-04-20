package bg.tu_varna.sit.util.tokenizers;

public class TokenizerFactory {
    /**
     * Метод фабрика за токенизер отговарящ на операционната система на потребителя.
     * @return Токенизер за Windows или Linux/Unix-like зависимо от операционната система на потребителя
     */
    public static Tokenizer getTokenizer() {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        if (isWindows) {
            return new TokenizerWindows();
        } else {
            return new TokenizerLinux(); // MacOS is UNIX-like so it uses the same tokenizer
        }
    }
}
