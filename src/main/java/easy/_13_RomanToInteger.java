package easy;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/22/19.
 * <p>
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * </p>
 */
public class _13_RomanToInteger {

    // core logic: compare the current and previous characters, and do the sum / difference from the result appropriately (preferred approach)
    // TC: O(n)
    private static int romanToInt(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int prev = map.get(s.charAt(0));

        for (int i = 1; i < s.toCharArray().length; i++) {
            int current = map.get(s.charAt(i));

            if (prev < current) {
                result -= prev;
            } else {
                result += prev;
            }
            prev = current;
        }
        result += prev;    // to account for last character after exiting the loop
        return result;
    }

    // core logic: exactly same as the previous approach, except that the last character corner case is included in the loop itself
    // TC: O(n)
    private static int romanToInt1(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        result = map.get(s.charAt(s.length() - 1));   // get the index of last character as the result as the loop doesn't process the last character and it however needs to be added
        for (int i = 0; i < s.toCharArray().length - 1; i++) {
            int current = map.get(s.charAt(i));
            int next = map.get(s.charAt(i + 1));

            if (current < next) {
                result -= current;
            } else {
                result += current;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        // test method: 1
        assertEquals(romanToInt("III"), 3);
        assertEquals(romanToInt("IV"), 4);
        assertEquals(romanToInt("IX"), 9);
        assertEquals(romanToInt("LVIII"), 58);
        assertEquals(romanToInt("MCMXCIV"), 1994);
        assertEquals(romanToInt("XLVIII"), 48);

        // test method: 1
        assertEquals(romanToInt1("III"), 3);
        assertEquals(romanToInt1("IV"), 4);
        assertEquals(romanToInt1("IX"), 9);
        assertEquals(romanToInt1("LVIII"), 58);
        assertEquals(romanToInt1("MCMXCIV"), 1994);
        assertEquals(romanToInt1("XLVIII"), 48);
    }
}
