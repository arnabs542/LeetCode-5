package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 3/4/20.
 * <p>
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * </p>
 */
public class _395_Longest_Substring_K_Rep {
    private static int longestSubstring(String s, int k) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        int idx = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (arr[s.charAt(i) - 'a'] < k) {
                max = Math.max(max, longestSubstring(s.substring(idx, i), k));
                idx = i + 1;
            }
        }
        return idx == 0 ? s.length() : Math.max(max, longestSubstring(s.substring(idx), k));
    }

    public static void main(String[] args) {
        assertEquals(longestSubstring("aaabb", 3), 3);
        assertEquals(longestSubstring("ababbc", 2), 5);
    }
}