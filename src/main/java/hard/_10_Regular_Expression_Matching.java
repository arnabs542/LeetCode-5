package hard;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 8/21/19.
 * <p>
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * </p>
 */
public class _10_Regular_Expression_Matching {
    // dp: TC: O(m * n), SC: O(m * n)
    // see https://www.youtube.com/watch?v=l3hda49XcDE for more details
    private static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // to handle the cases like p = a*b or a*b*c and s = ""  (these patters should still match the empty string)
        for (int i = 1; i < dp[0].length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // if string and pattern have a same character or pattern has '.' (matches any character in string), chose whatever dp value for the previous character in the string and regex
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {    // neither matching nor special characters
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        assertFalse(isMatch("aa", "a"));
        assertTrue(isMatch("aa", "a*"));
        assertTrue(isMatch("ab", ".*"));
        assertTrue(isMatch("aab", "c*a*b"));
        assertFalse(isMatch("mississippi", "mis*is*p*."));
        assertTrue(isMatch("", "c*a*b*"));
    }
}