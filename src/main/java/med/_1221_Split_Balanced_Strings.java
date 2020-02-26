package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by z002jsf on 2/25/20.
 * <p>
 * Balanced strings are those who have equal quantity of 'L' and 'R' characters.
 * Given a balanced string s split it in the maximum amount of balanced strings.
 * Return the maximum amount of splitted balanced strings.
 * </p>
 */
public class _1221_Split_Balanced_Strings {
    // core logic: keep a count of L and right chars and whenever they are equal, increment the result count (which means that's the min possible balanced string that could be split)
    // TC: O(n)
    private static int balancedStringSplit(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int rCount = 0, lCount = 0;
        int maxBalancedCount = 0;
        for (Character ch : s.toCharArray()) {
            if (ch.equals('L')) {
                lCount++;
            } else {
                rCount++;
            }
            if (lCount == rCount) {
                maxBalancedCount++;
            }
        }
        return maxBalancedCount;
    }

    // core logic: same as above, except that we only use one counter here. for left chars increment the counter, and for right chars, decrement the counter and when the counter is zero (which means till current char its balanced), increment the result
    // TC: O(n)
    private static int balancedStringSplit2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int balancedCount = 0;
        int maxBalancedCount = 0;
        for (Character ch : s.toCharArray()) {
            if (ch.equals('L')) {
                balancedCount++;
            } else {
                balancedCount--;
            }
            if (balancedCount == 0) {
                maxBalancedCount++;
            }
        }
        return maxBalancedCount;
    }

    public static void main(String[] args) {
        assertEquals(balancedStringSplit("RLRRLLRLRL"), 4);
        assertEquals(balancedStringSplit("RLLLLRRRLR"), 3);
        assertEquals(balancedStringSplit("LLLLRRRR"), 1);
        assertEquals(balancedStringSplit("RLRRRLLRLL"), 2);
    }
}