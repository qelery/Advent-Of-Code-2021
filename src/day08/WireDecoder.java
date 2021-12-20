package day08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WireDecoder {

    public static int NUM_SEGMENTS_DIGIT_1 = 2;
    public static int NUM_SEGMENTS_DIGIT_4 = 3;
    public static int NUM_SEGMENTS_DIGIT_7 = 4;
    public static int NUM_SEGMENTS_DIGIT_8 = 7;
    public final Map<Integer, Integer> digitToEncoded;
    public final Map<Integer, Integer> encodedToDigit;

    public WireDecoder(List<String> wires) {
        this.digitToEncoded = new HashMap<>();
        this.encodedToDigit = new HashMap<>();
        mapUniqueSegmentCountEncodings(wires);
        mapNonUniqueSegmentCountEncodings(wires);
    }

    public int decode(List<String> wires) {
        StringBuilder sb = new StringBuilder();
        for (String wire : wires) {
            int encodedWire = encode(wire);
            sb.append(encodedToDigit.get(encodedWire));
        }
        return Integer.parseInt(sb.toString());
    }

    public int encode(String wire) {
        int value = 0;
        for (char c : wire.toCharArray()) {
            int offset = c - 'a';
            value += Math.pow(2, offset);
        }
        return value;
    }

    private void mapUniqueSegmentCountEncodings(List<String> wires) {
        for (String wire : wires) {
            int encodedValue = encode(wire);
            int segmentCount = wire.length();
            switch (segmentCount) {
                case 2 -> {
                    digitToEncoded.put(1, encodedValue);
                    encodedToDigit.put(encodedValue, 1);
                }
                case 3 -> {
                    digitToEncoded.put(7, encodedValue);
                    encodedToDigit.put(encodedValue, 7);
                }
                case 4 -> {
                    digitToEncoded.put(4, encodedValue);
                    encodedToDigit.put(encodedValue, 4);
                }
                case 7 -> {
                    digitToEncoded.put(8, encodedValue);
                    encodedToDigit.put(encodedValue, 8);
                }
            }
        }
    }

    private void mapNonUniqueSegmentCountEncodings(List<String> wires) {
        List<String> wiresFiveOrMoreSegments = wires.stream()
                .filter(segments -> segments.length() == 5 || segments.length() == 6)
                .toList();
        int fourEncoded = digitToEncoded.get(4);
        int sevenEncoded = digitToEncoded.get(7);
        for (String wire : wiresFiveOrMoreSegments) {
            int encodedWire = encode(wire);
            int numSegmentsDifferentFromDigitFour = Integer.bitCount(encodedWire ^ fourEncoded);
            int numSegmentsDifferentFromDigitSeven = Integer.bitCount(encodedWire ^ sevenEncoded);
            int digit = -1;
            if (numSegmentsDifferentFromDigitFour == 4 && numSegmentsDifferentFromDigitSeven == 3) {
                digit = 0;
            } else if (numSegmentsDifferentFromDigitFour == 5 && numSegmentsDifferentFromDigitSeven == 4) {
                digit = 2;
            } else if (numSegmentsDifferentFromDigitFour == 3 && numSegmentsDifferentFromDigitSeven == 2) {
                digit = 3;
            } else if (numSegmentsDifferentFromDigitFour == 3 && numSegmentsDifferentFromDigitSeven == 4) {
                digit = 5;
            } else if (numSegmentsDifferentFromDigitFour == 4 && numSegmentsDifferentFromDigitSeven == 5) {
                digit = 6;
            } else if (numSegmentsDifferentFromDigitFour == 2 && numSegmentsDifferentFromDigitSeven == 3) {
                digit = 9;
            }
            digitToEncoded.put(digit, encodedWire);
            encodedToDigit.put(encodedWire, digit);
        }
    }

}
