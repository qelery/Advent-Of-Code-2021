package day01;

import util.Conversions;
import util.DailyPuzzle;

import java.util.List;

public class SonarSweepPuzzle extends DailyPuzzle {

    public static void main(String[] args) {
        List<String> lines = readFile("day01.data");
        int[] numbers = Conversions.convertToIntArray(lines);
        System.out.println("Number of increasing: " + countIncreasing(numbers));
    }

    public static int countIncreasing(int[] numbers) {
        int count = 0;
        int last = numbers[0];
        for (int current : numbers) {
            if (current > last) count++;
            last = current;
        }
        return count;
    }
}
