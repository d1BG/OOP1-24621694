package bg.tu_varna.sit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MusicPlaylists musicPlaylists = new MusicPlaylists();
        do {
            Commands commands = new Commands();

            System.out.print("Music Playlist > ");
            String currentLine = br.readLine();
            boolean inQuotes = false;

            ArrayList<String> tokens = new ArrayList<>();
            StringBuilder currToken = new StringBuilder();

             /*
                TODO: Linux file paths can include quotes,
                 so alongside with this, I also need to add
                 escape characters..
             */

            for (int i = 0; i < currentLine.length(); i++) {
                switch (currentLine.charAt(i)) {
                    case '"':
                        inQuotes = !inQuotes;
                        break;
                    case ' ':
                        if (inQuotes) {
                            currToken.append(currentLine.charAt(i));
                        } else {
                            tokens.add(currToken.toString());
                            currToken = new StringBuilder();
                        }
                        break;
                    default:
                        currToken.append(currentLine.charAt(i));
                }
            }
            tokens.add(currToken.toString()); // add the last token

            commands.exec(tokens.getFirst(), tokens);

            // CmdLineParser parser = new CmdLineParser(tokens, musicPlaylists);
        } while (true);
    }
}
