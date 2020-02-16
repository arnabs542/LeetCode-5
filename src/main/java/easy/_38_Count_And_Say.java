package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/16/20.
 * <p>
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively, in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.
 * </p>
 */
public class _38_Count_And_Say {
    // core logic: nth value is derived from n-1th value, and n-1th value is derived from n-2th value, and so on..
    // so start from 1 and keep calculating 2nd, 3rd,... till nth number
    // TC: O(2^n): due to nature of the problem, this is difficult to solve as it depends on the length of previously calculated string and the next string could grow exponentially if the characters in the prev string are different (eg: n-1th level: 01, nth level will be: 1011 which means length could double / exponentially grow)
    private static String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {   // calculate nth number
            result = countAndSayHelper(result);
        }
        return result;
    }

    // helper method to calculate the output value at the given level
    // iterate through the given string and for every character, keep a count and whenever the character changes, add the count of prev char and char itself to the result. don't forget to reset the count after appending to result
    private static String countAndSayHelper(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        char ch = string.charAt(0);
        int count = 1;
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) == ch) {
                count++;
            } else {     // new char encountered
                stringBuilder.append(count);
                stringBuilder.append(ch);
                ch = string.charAt(i);
                count = 1;   // reset the count when new character encountered
            }
        }
        stringBuilder.append(count);  // append the count and char to the result for the last char
        stringBuilder.append(ch);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        assertEquals(countAndSay(1), "1");
        assertEquals(countAndSay(4), "1211");
        assertEquals(countAndSay(5), "111221");
    }
}