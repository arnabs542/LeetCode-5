package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/6/19.
 * <p>
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * </p>
 */
public class _171_Excel_Sheet_Column_Number {

    // core logic: this is similar to binary to decimal conversion
    // example 1: CDA -> 26^2 * 3 + 26^1 * 4 + 26^0 * 1
    // example 2: ZY -> 26^1 * 26 + 26^0 * 25
    private static int titleToNumber(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int charDigit = s.charAt(s.length() - 1 - i) - 'A' + 1;
            result += Math.pow(26, i) * charDigit;
        }
        return result;
    }

    // similar to above approach, just a different style of writing (no need of math.pow)
    private static int titleToNumber2(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result * 26 + (s.charAt(i) - 'A' + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(titleToNumber("A"), 1);
        assertEquals(titleToNumber("AB"), 28);
        assertEquals(titleToNumber("ZY"), 701);
        assertEquals(titleToNumber("CDA"), 2133);

        // test method: 2
        assertEquals(titleToNumber2("A"), 1);
        assertEquals(titleToNumber2("AB"), 28);
        assertEquals(titleToNumber2("ZY"), 701);
        assertEquals(titleToNumber2("CDA"), 2133);
    }
}
