package day10;

import puzzleutils.DailyPuzzle;

import java.util.*;

/**
 * https://adventofcode.com/2021/day/10
 */
public class SyntaxScoringPuzzle extends DailyPuzzle {

    static Map<Character, Character> forwardBrackets;
    static Map<Character, Character> backwardBrackets;
    static Map<Character, Integer> scoring;

    static {
        forwardBrackets = new HashMap<>();
        forwardBrackets.put('(', ')');
        forwardBrackets.put('[', ']');
        forwardBrackets.put('{', '}');
        forwardBrackets.put('<', '>');

        backwardBrackets = new HashMap<>();
        backwardBrackets.put(')', '(');
        backwardBrackets.put(']', '[');
        backwardBrackets.put('}', '{');
        backwardBrackets.put('>', '<');

        scoring = new HashMap<>();
        scoring.put(')', 3);
        scoring.put(']', 57);
        scoring.put('}', 1197);
        scoring.put('>', 25137);
        scoring.put('(', 1);
        scoring.put('[', 2);
        scoring.put('{', 3);
        scoring.put('<', 4);
    }

    /**
     * Explanation - For part 1, iterate through the string pushing forward
     * brackets onto a stack. When a back bracket is encountered, check if
     * it belongs to the forward bracket at the top of the stack. If yes,
     * pop off the top forward bracket and continue. If not, score the
     * closing bracket and stop iterating. Part 2 is the same, except when
     * a bracket mismatch is found, stop iterating, and reverse the forward
     * brackets on the stack to get all the closing brackets. Score all
     * brackets, then take the median.
     */
    public static void main(String[] args) {
        List<String> lines = readFile("day10.txt");
        System.out.println("Syntax Error Score: " + scoreSyntaxErrors(lines));
        System.out.println("Closing Characters Score: " + getMiddleClosingBracketScore(lines));
    }

    public static long scoreSyntaxErrors(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : line.toCharArray()) {
                if (forwardBrackets.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (stack.pop() != backwardBrackets.get(c)) {
                        total += scoring.get(c);
                        break;
                    }
                }
            }
        }
        return total;
    }

    public static long getMiddleClosingBracketScore(List<String> lines) {
        List<Long> scores = new ArrayList<>();
        for (String line : lines) {
            long score = scoreValidClosingBrackets(line);
            if (score == 0) continue;
            scores.add(score);
        }
        Collections.sort(scores);
        return scores.get(scores.size() / 2);
    }

    private static long scoreValidClosingBrackets(String incompleteBrackets) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : incompleteBrackets.toCharArray()) {
            if (forwardBrackets.containsKey(c)) {
                stack.push(c);
            } else if (stack.peek() == backwardBrackets.get(c)) {
                stack.pop();
            } else {
                stack.clear();
                break;
            }
        }
        long score = 0;
        while (!stack.isEmpty()) {
            score = score * 5 + scoring.get(stack.pop());
        }
        return score;
    }
}
