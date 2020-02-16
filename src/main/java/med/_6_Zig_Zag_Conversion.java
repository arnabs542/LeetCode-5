package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/15/20.
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * </p>
 */
public class _6_Zig_Zag_Conversion {
    // core logic: visit by column. create numRows of string builders, iterate through the string and add each character to the string builder associated to the corresponding row
    // TC: O(n)
    private static String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return "";
        }

        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            stringBuilders[i] = new StringBuilder();  // create numRows of string builders
        }

        int length = s.length();
        int i = 0;  // i is used to track the character index in the string
        while (i < length) {
            for (int index = 0; index < numRows && i < length; index++) {   // top to bottom
                stringBuilders[index].append(s.charAt(i++));
            }

            for (int index = numRows - 2; index >= 1 && i < length; index--) {   // slantwise up  (index stops at 1 as the top loop takes care of filling the 0th index again)
                stringBuilders[index].append(s.charAt(i++));
            }
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder stringBuilder : stringBuilders) {
            result.append(stringBuilder);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        assertEquals(convert("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
        assertEquals(convert("PAYPALISHIRING", 4), "PINALSIGYAHRPI");
    }
}