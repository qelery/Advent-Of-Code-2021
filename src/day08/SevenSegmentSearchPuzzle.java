package day08;

import puzzleutils.DailyPuzzle;

import java.util.*;

/**
 * https://adventofcode.com/2021/day/8
 */
public class SevenSegmentSearchPuzzle extends DailyPuzzle {

    /**
     * Explanation - The instruction explains that digits 1, 4, 7, and 8
     * have a unique number of segments. For part 1, count the occurrences
     * of those unique number of segments in the output. For part 2,
     * derive which patterns are 1, 4, 7, and 8 using the logic above.
     * To find which patterns represent the remaining digits, use the fact
     * that each digit has a unique segment pattern, with a maximum of 7
     * segments total. Encode the segment pattern to a 7 bit number. From
     * there you can use the xor operator to get the differing segments
     * between each digit, and after that derive which patterns match which
     * remaining digits.
     */
    public static void main(String[] args) {
        List<String> lines = readFile("day08.txt");
        Set<Integer> targetSegmentCounts = Set.of(
                WireDecoder.NUM_SEGMENTS_DIGIT_1,
                WireDecoder.NUM_SEGMENTS_DIGIT_4,
                WireDecoder.NUM_SEGMENTS_DIGIT_7,
                WireDecoder.NUM_SEGMENTS_DIGIT_8
        );
        System.out.println("Part 1 - Occurrences of digit 1, 4, 7, or 8 in outputs: " +
                countOccurrences(lines, targetSegmentCounts));
        System.out.println("Part 2 - Sum of all panel outputs: " + sumPanelOutputs(lines));
    }

    public static int countOccurrences(List<String> lines, Set<Integer> targetSegmentCounts) {
        int count = 0;
        for (String line : lines) {
            String[] outputs = line.split(" \\| ")[1].split(" ");
            for (String o : outputs) {
                if (targetSegmentCounts.contains(o.length())) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int sumPanelOutputs(List<String> lines) {
        List<Integer> decodedOutputs = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            List<String> inputWires = Arrays.stream(parts[0].split(" ")).toList();
            List<String> outputWires = Arrays.stream(parts[1].split(" ")).toList();
            WireDecoder wireDecoder = new WireDecoder(inputWires);
            int decoded = wireDecoder.decode(outputWires);
            decodedOutputs.add(decoded);
        }
        return decodedOutputs.stream().mapToInt(Integer::intValue).sum();
    }
}

