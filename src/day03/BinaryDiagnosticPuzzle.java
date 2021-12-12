package day03;

import util.DailyPuzzle;

import java.util.List;

public class BinaryDiagnosticPuzzle extends DailyPuzzle {

    public static void main(String[] args) {
        List<String> lines = readFile("day03.txt");
        System.out.println("Part 1 - Final depth times horizontal position: " + calculatePowerConsumption(lines));
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
}
