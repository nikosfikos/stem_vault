package dev.stemvault;
import dev.stemvault.analyzer.FileHasher;
import dev.stemvault.scanner.FileScanner;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Path> paths = new FileScanner().scan(Path.of("."));
            for (Path path : paths) {
                System.out.println(path);
                System.out.println(FileHasher.hash(path));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
