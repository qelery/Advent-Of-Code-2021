package day03;

import util.Conversions;
import util.DailyPuzzle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryDiagnosticPuzzle extends DailyPuzzle {

    public static void main(String[] args) {
        List<String> lines = readFile("day03.txt");
        System.out.println("Part 1 - Final depth times horizontal position: " + calculatePowerConsumption(lines));
    }

    public static long calculatePowerConsumption(List<String> lines) {
        int lengthInput = lines.get(0).length();
        int[] mostCommonBit = new int[lengthInput];
        for (String line : lines) {
            for (int j = 0; j < lengthInput; j++) {
                char c = line.charAt(j);
                if (c == '1') {
                    mostCommonBit[j]++;
                } else {
                    mostCommonBit[j]--;
                }
            }
        }
        String gamma = Arrays.stream(mostCommonBit).map(x -> x > 0 ? 1 : 0).mapToObj(Integer::toString).collect(Collectors.joining());
        String epsilon = Arrays.stream(mostCommonBit).map(x -> x > 0 ? 0 : 1).mapToObj(Integer::toString).collect(Collectors.joining());
        return Long.parseLong(gamma, 2) * Long.parseLong(epsilon, 2);
    }
}
