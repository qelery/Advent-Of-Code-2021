package day05;

import util.DailyPuzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://adventofcode.com/2021/day/5
 */
public class HydrothermalVenturePuzzle extends DailyPuzzle {
    record Point(int x, int y) {
    }

    record StraightLine(Point point1, Point point2) {
    }

    public static void main(String[] args) {
        List<String> text = readFile("day05.txt");
        List<StraightLine> straightLines = parseHorizontalAndVerticalLines(text);
        System.out.println("Part 1 - Score of first winning board: " + countOverlappingPoints(straightLines));
    }

    public static List<StraightLine> parseHorizontalAndVerticalLines(List<String> text) {
        List<StraightLine> straightLines = new ArrayList<>();
        for (String textLine : text) {
            String[] parts = textLine.split(" -> ");
            String[] start = parts[0].split(",");
            String[] end = parts[1].split(",");
            int startX = Integer.parseInt(start[0]);
            int startY = Integer.parseInt(start[1]);
            int endX = Integer.parseInt(end[0]);
            int endY = Integer.parseInt(end[1]);
            if (startX != endX && startY != endY) {
                continue;
            }
            StraightLine strLine = new StraightLine(new Point(startX, startY), new Point(endX, endY));
            straightLines.add(strLine);
        }
        return straightLines;
    }

    public static int countOverlappingPoints(List<StraightLine> straightLines) {
        Map<Point, Integer> overlappingPoints = new HashMap<>();
        for (StraightLine line : straightLines) {
            if (line.point1.x == line.point2.x) {
                int y1 = Math.min(line.point1.y, line.point2.y);
                int y2 = Math.max(line.point1.y, line.point2.y);
                for (int i = y1; i < y2 + 1; i++) {
                    Point p = new Point(line.point1.x, i);
                    overlappingPoints.putIfAbsent(p, 0);
                    overlappingPoints.merge(p, 1, Integer::sum);
                }
            } else {
                int x1 = Math.min(line.point1.x, line.point2.x);
                int x2 = Math.max(line.point1.x, line.point2.x);
                for (int i = x1; i < x2 + 1; i++) {
                    Point p = new Point(i, line.point1.y);
                    overlappingPoints.putIfAbsent(p, 0);
                    overlappingPoints.merge(p, 1, Integer::sum);
                }
            }
        }
        return (int) overlappingPoints.values().stream().filter(t -> t > 1).count();
    }
}
