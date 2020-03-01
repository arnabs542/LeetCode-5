package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/5/19.
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * </p>
 */
public class _91_DecodeWays {
    // DP: Tabulation: Bottom Up approach
    private static int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));
            if (oneDigit >= 1 && oneDigit <= 9) {   // all the mappings are valid for numbers>1
                dp[i] += dp[i - 1];
            }
            if (twoDigits >= 10 && twoDigits <= 26) {  // 2 digit combinations are valid only in this range
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        assertEquals(numDecodings("12"), 2);
        assertEquals(numDecodings("226"), 3);
        assertEquals(numDecodings("2264"), 3);
    }
}
