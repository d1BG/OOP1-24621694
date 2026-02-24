package bg.tu_varna.sit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MusicPlaylists musicPlaylists = new MusicPlaylists();
        Commands commands = new Commands(musicPlaylists);

        do {
            System.out.print("Music Playlist > ");
            String currentLine = br.readLine();
            boolean inQuotes = false;

            ArrayList<String> tokens = new ArrayList<>();
            StringBuilder currToken = new StringBuilder();

            for (int i = 0; i < currentLine.length(); i++) {
                switch (currentLine.charAt(i)) {
                    case '"':
                        inQuotes = !inQuotes;
                        break;
                    case ' ':
                        if (inQuotes) {
                            currToken.append(currentLine.charAt(i));
                        } else if (!currToken.isEmpty()) {
                            tokens.add(currToken.toString());
                            currToken = new StringBuilder();
                        }
                        break;
                    default:
                        currToken.append(currentLine.charAt(i));
                }
            }
            if (!currToken.isEmpty()) {
                tokens.add(currToken.toString()); // add the last token
            }

            commands.exec(tokens.getFirst(), tokens);
        } while (true);
    }
}
