package day04;

import util.DailyPuzzle;

import java.util.*;

/**
 * https://adventofcode.com/2021/day/4
 */
public class GiantSquidPuzzle extends DailyPuzzle {

    /**
     * Explanation - construct an object called Bingo Board that has
     * a List<Set<Integer>> structure representing the values in columns,
     * and use the same structure for the rows. Checking a marking off
     * a number on a card means  iterating over each set and removing
     * the number if it's present. A card wins when one of its sets
     * is emptied.
     */
    public static void main(String[] args) {
        List<String> lines = readFile("day04.txt");
        int[] calledNumbers = parseCalledNumbers(lines);
        List<BingoBoard> bingoBoards = parseBoards(lines);
        System.out.println("Part 1 - Score of first winning board: " + scoreFirstWinningBoard(calledNumbers, bingoBoards));
        System.out.println("Part 2 - Score of last winning board: " + scoreLastWinningBoard(calledNumbers, bingoBoards));
    }

    public static int[] parseCalledNumbers(List<String> lines) {
        return Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public static List<BingoBoard> parseBoards(List<String> lines) {
        List<BingoBoard> boards = new ArrayList<>();
        List<Set<Integer>> rows = new ArrayList<>();
        List<Set<Integer>> cols = new ArrayList<>();
        for (int i = 2; i < lines.size(); i++) {
            if (lines.get(i).isBlank()) {
                boards.add(new BingoBoard(cols, rows));
                rows = new ArrayList<>();
                cols = new ArrayList<>();
                continue;
            }
            Set<Integer> row = new HashSet<>();
            int j = 0;
            for (String s : lines.get(i).trim().split("\\s+")) {
                Integer x = Integer.valueOf(s);
                row.add(x);
                if (j + 1 > cols.size()) {
                    cols.add(new HashSet<>());
                }
                cols.get(j).add(x);
                j++;
            }
            rows.add(row);
        }
        boards.add(new BingoBoard(cols, rows));
        return boards;
    }

    public static int scoreFirstWinningBoard(int[] calledNumbers, List<BingoBoard> boards) {
        return scoreNthWinningBoard(calledNumbers, boards, 1);
    }

    public static int scoreLastWinningBoard(int[] calledNumbers, List<BingoBoard> boards) {
        return scoreNthWinningBoard(calledNumbers, boards, boards.size() - 1);
    }

    private static int scoreNthWinningBoard(int[] calledNumbers, List<BingoBoard> boards, int n) {
        int winners = 0;
        for (int x : calledNumbers) {
            for (BingoBoard board : boards) {
                if (!board.hasWon() && board.markNumber(x) && board.checkForWin()) {
                    if (++winners == n) {
                        return x * board.sumUnmarkedNumbers();
                    }
                }
            }
        }
        return -1;
    }
}
