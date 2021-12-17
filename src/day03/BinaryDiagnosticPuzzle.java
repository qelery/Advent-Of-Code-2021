package day03;

import util.DailyPuzzle;

import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnosticPuzzle extends DailyPuzzle {

    public static void main(String[] args) {
        List<String> lines = readFile("day03.txt");
        System.out.println("Part 1 - Power Consumption: " + calculatePowerConsumption(lines));
        System.out.println("Part 1 - Life Support Rating: " + calculateLifeSupportRating(lines));
    }

    /**
     * Time complexity: O(n * m), where n is the length of lines, and m is the length of a line
     * Space complexity: O(n)
     */
    public static long calculatePowerConsumption(List<String> lines) {
        int lineLength = lines.get(0).length();
        StringBuilder gammaBinary = new StringBuilder();
        StringBuilder epsilonBinary = new StringBuilder();
        for (int i = 0; i < lineLength; i++) {
            int ones = 0;
            int zeros = 0;
            for (String line : lines) {
                char c = line.charAt(i);
                if (c == '0') zeros++;
                else ones++;
            }
            gammaBinary.append(zeros > ones ? "0" : "1");
            epsilonBinary.append(zeros > ones ? "1" : "0");
        }
        long gamma = Long.parseLong(gammaBinary.toString(), 2);
        long epsilon = Long.parseLong(epsilonBinary.toString(), 2);
        return gamma * epsilon;
    }

    public static long calculateLifeSupportRating(List<String> lines) {
        String oxygen = filterSuccessiveDigits(lines, true);
        String co2 = filterSuccessiveDigits(lines, false);
        return Long.parseLong(oxygen, 2) * Long.parseLong(co2, 2);
    }

    private static String filterSuccessiveDigits(List<String> lines, boolean isMostCommon) {
        List<String> remainingLines = new ArrayList<>(lines);
        int place = 0;
        while (remainingLines.size() > 1) {
            remainingLines = filterLines(remainingLines, place, isMostCommon);
            place++;
        }
        return remainingLines.get(0);
    }

    private static List<String> filterLines(List<String> lines, int place, boolean isMostCommon) {
        List<String> zeros = new ArrayList<>();
        List<String> ones = new ArrayList<>();
        for (String line : lines) {
            char c = line.charAt(place);
            if (c == '0') {
                zeros.add(line);
            } else {
                ones.add(line);
            }
        }
        if (isMostCommon) {
            return zeros.size() > ones.size() ? zeros : ones;
        }
        return zeros.size() > ones.size() ? ones : zeros;
    }
}
