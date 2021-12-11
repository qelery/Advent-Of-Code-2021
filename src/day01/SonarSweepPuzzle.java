package day01;

import util.Conversions;
import util.DailyPuzzle;

import java.util.Arrays;
import java.util.List;

public class SonarSweepPuzzle extends DailyPuzzle {

    public static void main(String[] args) {
        List<String> lines = readFile("day01.data");
        int[] numbers = Conversions.convertToIntArray(lines);
        System.out.println("Number of increasing: " + countIncreasing(numbers));
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int countIncreasing(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        int count = 0;
        int last = numbers[0];
        for (int current : numbers) {
            if (current > last) count++;
            last = current;
        }
        return count;
    }
}
