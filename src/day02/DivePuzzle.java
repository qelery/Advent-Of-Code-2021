package day02;

import util.DailyPuzzle;

import java.util.List;

public class DivePuzzle extends DailyPuzzle {

    public record Point(int x, int y) {
    }

    public static void main(String[] args) {
        List<String> lines = readFile("day02.txt");
        Point point = parseInstructions1(lines);
        System.out.println("Part 1 - Final depth time horizontal position: " + point.x * point.y);
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
}
