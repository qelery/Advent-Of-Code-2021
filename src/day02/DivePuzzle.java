package day02;

import util.DailyPuzzle;

import java.util.List;

public class DivePuzzle extends DailyPuzzle {

    public record Point(int x, int y) {
    }

    public static void main(String[] args) {
        List<String> lines = readFile("day02.txt");
        Point point1 = parseInstructions1(lines);
        System.out.println("Part 1 - Final depth time horizontal position: " + point1.x * point1.y);
        Point point2 = parseInstructions2(lines);
        System.out.println("Part 2 - Final depth time horizontal position: " + point2.x * point2.y);
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static Point parseInstructions1(List<String> instructions) {
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
        return new Point(x, y);
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static Point parseInstructions2(List<String> instructions) {
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
        return new Point(x, y);
    }
}
