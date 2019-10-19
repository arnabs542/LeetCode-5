package random_practice.microsoftassessment;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/18/19.
 * <p>
 * Given a string s containing only as and bs, find longest substring of s such that s does not contain more than two contiguous occurrences of a and b.
 * </p>
 */
public class Longest_Substring_Without_2_Cons_Occur {
    private static String longestSubstring(String input) {
        if (input == null || input.length() < 3) {
            return input;
        }

        int l = 0, cnt = 1, lastSeen = 0;
        String res = "";
        for (int i = 1; i < input.length(); i++) {
            char cur = input.charAt(i);
            if (cur == input.charAt(i - 1)) {
                cnt++;
            } else {
                lastSeen = i;
                cnt = 1;
            }
            if (cnt > 2 && l < lastSeen) {
                l = lastSeen;
            }
            while (cnt > 2) {
                l++;
                cnt--;
            }
            if (i - l + 1 > res.length()) {
                res = input.substring(l, i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        assertEquals(longestSubstring("aabbaaaaabb"), "aabbaa");
        assertEquals(longestSubstring("aabbaabbaabbaa"), "aabbaabbaabbaa");
    }
}
