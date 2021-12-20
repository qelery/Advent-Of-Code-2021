package day07;

import puzzleutils.DailyPuzzle;

import java.util.Arrays;

/**
 * https://adventofcode.com/2021/day/7
 */
public class TreacheryOfWhalesPuzzle extends DailyPuzzle {

    /**
     * Explanation - Part 1 can be calculated by realizing that the
     * position that costs the least amount of fuel to move all crabs
     * to is the median. Part 2 is a minimizing nth triangle number
     * problem. https://en.wikipedia.org/wiki/Triangular_number
     */
    public static void main(String[] args) {
        String delimitedNumbers = readFile("day07.txt").get(0);
        int[] nums = parseIntegers(delimitedNumbers);
        System.out.println("Part 1 - Cheapest fuel expenditure: " + calculateFuelCost(nums));
        System.out.println("Part 2 - Cheapest fuel expenditure: " + calculateFuelCostNthTriangle(nums));
    }

    public static int[] parseIntegers(String delimitedNumbers) {
        return Arrays.stream(delimitedNumbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int calculateFuelCost(int[] num) {
        int[] numCopy = num.clone();
        Arrays.sort(numCopy);
        int median = numCopy[numCopy.length / 2];
        return Arrays.stream(numCopy).map(n -> Math.abs(median - n)).sum();
    }

    public static int calculateFuelCostNthTriangle(int[] num) {
        int lowestFuelCost = Integer.MAX_VALUE;
        int min = Arrays.stream(num).min().orElseThrow(IllegalStateException::new);
        int max = Arrays.stream(num).max().orElseThrow(IllegalStateException::new);
        for (int i = min; i < max + 1; i++) {
            int totalFuel = 0;
            for (int n: num) {
                totalFuel += calculateNthTriangleNumber(i, n);
            }
            lowestFuelCost = Math.min(totalFuel, lowestFuelCost);
        }
        return lowestFuelCost;
    }

    public static int calculateNthTriangleNumber(int a, int b) {
        return Math.abs(a - b) * (Math.abs(a - b) + 1) / 2;
    }
}