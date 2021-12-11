package day01;

import util.Conversions;
import util.DailyPuzzle;

import java.util.List;

public class SonarSweepPuzzle extends DailyPuzzle {

    public static void main(String[] args) {
        List<String> lines = readFile("day01.txt");
        int[] numbers = Conversions.convertToIntArray(lines);
        System.out.println("Part 1 - Number of increasing: " + countIncreasingSlidingWindows(numbers, 1));
        System.out.println("Part 2 - Number of increasing sliding window size of 3: " +
                countIncreasingSlidingWindows(numbers, 3));
    }

    /**
     * Given SlidingWindowA and SlidingWindowB, all elements of the two windows overlap except
     * for the first element of SlidingWindowA and the last element of SlidingWindowB. Therefore,
     * the difference between the two windows is the difference between the first element of
     * SlidingWindowA and the last element of SlidingWindowB.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int countIncreasingSlidingWindows(int[] numbers, int windowSize) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = windowSize; i < numbers.length; i++) {
            if (numbers[i] > numbers[i - windowSize]) count++;
        }
        return count;
    }
}
