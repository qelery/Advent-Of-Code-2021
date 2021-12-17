package day04;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class BingoBoard {

    List<Set<Integer>> columns;
    List<Set<Integer>> rows;

    public BingoBoard(List<Set<Integer>> columns, List<Set<Integer>> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public boolean markNumber(int x) {
        return findNumberInColumn(x) && findNumberInRow(x);
    }

    public int sumUnmarkedNumbers() {
        return columns.stream().flatMap(Collection::stream).mapToInt(Integer::intValue).sum();
    }

    public boolean checkForWin() {
        return fullColumnMarked() || fullRowMarked();
    }

    private boolean findNumberInColumn(int x) {
        return columns.stream().anyMatch(set -> set.remove(x));
    }

    private boolean findNumberInRow(int x) {
        return rows.stream().anyMatch(set -> set.remove(x));
    }


    private boolean fullColumnMarked() {
        return columns.stream().anyMatch(Set::isEmpty);
    }

    private boolean fullRowMarked() {
        return rows.stream().anyMatch(Set::isEmpty);
    }

    @Override
    public String toString() {
        return "BingoBoard{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}';
    }
}
