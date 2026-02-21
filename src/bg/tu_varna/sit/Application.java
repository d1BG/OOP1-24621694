package bg.tu_varna.sit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("Music Playlist > ");
            String[] cmd = br.readLine().split(" ");
            CmdLineParser parser = new CmdLineParser(cmd);
        } while (true);
    }
}
