package day06;

import puzzleutils.DailyPuzzle;

import java.util.Arrays;

/**
 * https://adventofcode.com/2021/day/6
 */
public class Lanternfish extends DailyPuzzle {

    static int MAX_AGE = 8;

    /**
     * Explanation - Keep an array where the index represents the days the
     * fish has left until it reproduces. Load the starting fish into the
     * appropriate index by incrementing the value found at that index.
     * With each day that passes, shift the array to the left. Take the
     * number of fish at index 0 and increment index 6 by that amount
     * (this represents existing fish's timers restarting). Take the same
     * number and increment index 8 by that amount (this represents newly
     * spawned fish who will themselves reproduce after 8 days).
     */
    public static void main(String[] args) {
        String fish = readFile("day06.txt").get(0);
        long[] groupedTimers = groupReproductionTimers(fish);
        System.out.println("Fish after 80 days elapsed: " + simulateGrowth(groupedTimers, 80));
        System.out.println("Fish after 256 days elapsed: " + simulateGrowth(groupedTimers, 256));
    }

    public static long[] groupReproductionTimers(String fish) {
        int[] timers = Arrays.stream(fish.split(",")).mapToInt(Integer::parseInt).toArray();
        long[] groupedTimers = new long[MAX_AGE + 1];
        for (int timer : timers) {
            groupedTimers[timer]++;
        }
        return groupedTimers;
    }

    public static long simulateGrowth(long[] groupedTimers, int days) {
        long[] timers = groupedTimers.clone();
        for (int i = 0; i < days; i++) {
            long expiringTimers = timers[0];
            for (int j = 1; j < groupedTimers.length; j++) {
                timers[j - 1] = timers[j];
            }
            timers[8] = expiringTimers;
            timers[6] += expiringTimers;
        }
        return Arrays.stream(timers).sum();
    }
}
