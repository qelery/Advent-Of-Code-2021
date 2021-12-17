package day04;

import util.DailyPuzzle;

import java.util.*;

public class GiantSquidPuzzle  extends DailyPuzzle {

    public static void main(String[] args) {
        List<String> lines = readFile("day04.txt");
        int[] calledNumbers = parseCalledNumbers(lines);
        List<BingoBoard> bingoBoards = parseBoards(lines);
        System.out.println("Part 1 - Score of first winning board: " + scoreFirstWinningBoard(calledNumbers, bingoBoards));
    }

    private static int[] parseCalledNumbers(List<String> lines) {
        return Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
    }

    private static List<BingoBoard> parseBoards(List<String> lines) {
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
            for (String s: lines.get(i).trim().split("\\s+")) {
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
        for (int n: calledNumbers) {
            for (BingoBoard board: boards) {
                if (board.markNumber(n) && board.checkForWin()) {
                    return n * board.sumUnmarkedNumbers();
                }
            }
        }
        return -1;
    }
}
