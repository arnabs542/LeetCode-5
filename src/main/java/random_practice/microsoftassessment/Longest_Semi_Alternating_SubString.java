package random_practice.microsoftassessment;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/20/19.
 * <p>
 * Given a string, find its alternating sub string where it doesn't contain 3 identical consecutive characters ('a', 'b')
 * </p>
 */
public class Longest_Semi_Alternating_SubString {
    private static int longestAlternating(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int count = 1, left = 0, lastSeenIndex = 0;
        int res = 0;

        for (int right = 1; right < s.length(); right++) {
            char c = s.charAt(right);
            if (s.charAt(right - 1) == c) {
                count++;
            } else {
                count = 1;
                lastSeenIndex = right;
            }

            if (count > 2 && left < lastSeenIndex) {
                left = lastSeenIndex;
            }

            while (count > 2) {
                left++;
                count--;
            }
            // res = Math.max(res, right - left + 1);
            if ((right - left + 1) > res) {
                res = right - left + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        assertEquals(longestAlternating("baaabbabbb"), 7);
        assertEquals(longestAlternating("babba"), 5);
        assertEquals(longestAlternating("abaaaa"), 4);
    }
}