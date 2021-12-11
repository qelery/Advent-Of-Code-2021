package day02;

import util.DailyPuzzle;

import java.util.List;

public class DivePuzzle extends DailyPuzzle {

    public static void main(String[] args) {
        List<String> lines = readFile("day02.txt");
        System.out.println("Part 1 - Final depth times horizontal position: " + parseInstructions1(lines));
        System.out.println("Part 2 - Final depth times horizontal position: " + parseInstructions2(lines));
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int parseInstructions1(List<String> instructions) {
        int x = 0;
        int y = 0;
        for (String instr : instructions) {
            String[] parts = instr.split(" ");
            String direction = parts[0];
            int distance = Integer.parseInt(parts[1]);
            switch (direction) {
                case "up" -> y -= distance;
                case "down" -> y += distance;
                case "forward" -> x += distance;
                default -> throw new IllegalStateException("Invalid direction: " + direction);
            }
        }
        return x * y;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int parseInstructions2(List<String> instructions) {
        int x = 0;
        int y = 0;
        int aim = 0;
        for (String instr : instructions) {
            String[] parts = instr.split(" ");
            String direction = parts[0];
            int amount = Integer.parseInt(parts[1]);
            switch (direction) {
                case "up" -> aim -= amount;
                case "down" -> aim += amount;
                case "forward" -> {
                    x += amount;
                    y += aim * amount;
                }
                default -> throw new IllegalStateException("Invalid direction: " + direction);
            }
        }
        return x * y;
    }
}
