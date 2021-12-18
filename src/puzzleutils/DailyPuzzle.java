package puzzleutils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * https://adventofcode.com/2021/day/1
 */
public class DailyPuzzle {

    public static List<String> readFile(String filename) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get("input", filename));
        } catch (IOException ex) {
            System.out.println();
            ex.printStackTrace();
        }
        return lines;
    }
}
