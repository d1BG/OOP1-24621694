package bg.tu_varna.sit;

import bg.tu_varna.sit.commands.Commands;
import bg.tu_varna.sit.data.*;
import bg.tu_varna.sit.exceptions.*;
import bg.tu_varna.sit.util.Tokenizer;
import bg.tu_varna.sit.util.TokenizerWindows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MusicPlaylistsInterface musicPlaylists = new MusicPlaylists();
        Commands commands = new Commands(musicPlaylists);
        Tokenizer tokenizer = new TokenizerWindows();
        do {
            System.out.print("Music Playlist > ");
            String currentLine = br.readLine();
            tokenizer.clear();
            List<String> tokens = tokenizer.tokenize(currentLine);
            if (tokens.isEmpty()) { continue; }
            try {
                String result = commands.exec(tokens.getFirst(), tokens);
                System.out.println(result);
                if (result.equals("Exiting...")) {
                    break;
                }
            } catch (CommandException | SongException | PlaylistException e){
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
