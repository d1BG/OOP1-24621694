package bg.tu_varna.sit.util.tokenizers;

public class TokenizerFactory {
    public static Tokenizer getTokenizer() {

        boolean isWindows = System.getProperty("os.name").startsWith("Windows");

        if (isWindows) {
            return new TokenizerWindows();
        } else {
            return new TokenizerLinux(); // MacOS is UNIX-like so it uses the same tokenizer
        }
    }
}
