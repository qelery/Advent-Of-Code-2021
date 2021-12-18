package util;

import java.util.List;

public class Conversions {

    public static int[] convertToIntArray(List<String> strings) {
        return strings.stream().mapToInt(Integer::parseInt).toArray();
    }
}
