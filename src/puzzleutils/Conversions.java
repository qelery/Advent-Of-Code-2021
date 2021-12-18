package util;

import java.util.List;

public class Conversions {

    public static int[] convertToIntArray(List<String> strings) {
        return strings.stream().mapToInt(Integer::parseInt).toArray();
    }

//    public static int[][] convertToNestedInArray(List<String> strings) {
//        int[][] arr = new int[strings.size()][strings.get(0).length()];
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[0].length; j++) {
//                char c = strings.get(i).charAt(j);
//                arr[i][j] = c - '0';
//            }
//        }
//        return arr;
//    }
}
