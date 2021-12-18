package day05;

import puzzleutils.DailyPuzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://adventofcode.com/2021/day/5
 */
public class HydrothermalVenturePuzzle extends DailyPuzzle {

    /**
     * Explanation - Create a map that keeps track of the number of
     * times a point has been encountered. Iterate through all points
     * on all lines. Count the number of points that were encountered
     * more than once.
     */
    public static void main(String[] args) {
        List<String> text = readFile("day05.txt");
        List<StraightLine> straightLines = parseStraightLines(text);
        System.out.println("Part 1 - Number of overlapping points excluding diagonal lines: "
                + countOverlappingPoints(straightLines, true));
        System.out.println("Part 2 - Number of overlapping points all lines: "
                + countOverlappingPoints(straightLines, false));
    }

    public static List<StraightLine> parseStraightLines(List<String> text) {
        List<StraightLine> straightLines = new ArrayList<>();
        for (String textLine : text) {
            String[] parts = textLine.split(" -> ");
            String[] start = parts[0].split(",");
            String[] end = parts[1].split(",");
            int startX = Integer.parseInt(start[0]);
            int startY = Integer.parseInt(start[1]);
            int endX = Integer.parseInt(end[0]);
            int endY = Integer.parseInt(end[1]);
            straightLines.add(new StraightLine(new Point(startX, startY), new Point(endX, endY)));
        }
        return straightLines;
    }

    public static int countOverlappingPoints(List<StraightLine> straightLines, boolean excludeDiagonalLines) {
        Map<Point, Integer> overlappingPoints = new HashMap<>();
        for (StraightLine line : straightLines) {
            if (excludeDiagonalLines && (!line.isHorizontal() && !line.isVertical())) {
                continue;
            }
            for (Point p : line.getAllPoints()) {
                overlappingPoints.putIfAbsent(p, 0);
                overlappingPoints.merge(p, 1, Integer::sum);
            }
        }
        return (int) overlappingPoints.values().stream().filter(t -> t > 1).count();
    }
}
