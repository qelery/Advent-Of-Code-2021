package day09;

import puzzleutils.DailyPuzzle;
import puzzleutils.Point;

import java.util.*;
import java.util.stream.IntStream;

/**
 * https://adventofcode.com/2021/day/9
 */
public class SmokeBasinPuzzle extends DailyPuzzle {

    /**
     * Explanation - Create a map that maps points to height. For
     * part 1, iterate through the map's keys and identify the keys
     * for which all four of its neighbors (N, S, E, W) are at a
     * greater height. For part 2, iterate through the height map's
     * keys. Once a key is encountered that is < 9, use DFS to search
     * its neighbors and its neighbor's neighbors that are < 9. Keep
     * a set of all points/neighbors that have been visited so they
     * can be skipped as we iterate through the height map.
     */
    public static void main(String[] args) {
        List<String> lines = readFile("day09.txt");
        Map<Point, Integer> heightMap = parseLines(lines);
        System.out.println("Part 1 - Total Risk: " + calculateRisk(heightMap));
        System.out.println("Part 2 - Product of 3 largest basins: " + calculateBasinRisk(heightMap));
    }

    public static Map<Point, Integer> parseLines(List<String> lines) {
        Map<Point, Integer> map = new HashMap<>();
        int y = 0;
        for (String s : lines) {
            int x = 0;
            for (char c : s.toCharArray()) {
                int num = c - '0';
                map.put(new Point(x, y), num);
                x++;
            }
            y++;
        }
        return map;
    }

    // Task 1
    public static int calculateRisk(Map<Point, Integer> heightMap) {
        int total = 0;
        for (Map.Entry<Point, Integer> point : heightMap.entrySet()) {
            if (point.getValue() == 9)
                continue;
            int x = point.getKey().x();
            int y = point.getKey().y();
            int north = heightMap.getOrDefault(new Point(x, y - 1), Integer.MAX_VALUE);
            int south = heightMap.getOrDefault(new Point(x, y + 1), Integer.MAX_VALUE);
            int east = heightMap.getOrDefault(new Point(x + 1, y), Integer.MAX_VALUE);
            int west = heightMap.getOrDefault(new Point(x - 1, y), Integer.MAX_VALUE);
            if (IntStream.of(north, south, east, west).allMatch(neighbor -> neighbor > point.getValue())) {
                total += point.getValue() + 1;
            }
        }
        return total;
    }

    // Task 2
    public static int calculateBasinRisk(Map<Point, Integer> heightMap) {
        Set<Point> seen = new HashSet<>();
        List<Integer> basinSums = new ArrayList<>();
        for (Map.Entry<Point, Integer> point : heightMap.entrySet()) {
            if (point.getValue() < 9 && !seen.contains(point.getKey())) {
                basinSums.add(DFS(point.getKey(), heightMap, seen));
            }
        }
        basinSums.sort(Collections.reverseOrder());
        return basinSums.get(0) * basinSums.get(1) * basinSums.get(2);
    }

    private static int DFS(Point point, Map<Point, Integer> heightMap, Set<Point> seen) {
        int total = 0;
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(point);
        while (!stack.isEmpty()) {
            Point cur = stack.pop();
            if (heightMap.getOrDefault(cur, 9) == 9) {
                continue;
            }
            Point north = new Point(cur.x(), cur.y() - 1);
            Point south = new Point(cur.x(), cur.y() + 1);
            Point east = new Point(cur.x() + 1, cur.y());
            Point west = new Point(cur.x() - 1, cur.y());
            if (!seen.contains(cur)) {
                seen.add(cur);
                total++;
                stack.addAll(List.of(north, south, east, west));
            }
        }
        return total;
    }

}