package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/26/19.
 * <p>
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 * In one operation, you can choose any character of the string and change it to any other uppercase English character.
 * Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 * </p>
 */
public class _424_Longest_Repeating_Character_Replacement {
    private static int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }

        int left = 0;  // left index of the sliding window
        int maxUniqueCharCount = 0; // count of most unique chars in the window
        int max = 0;  // length of the longest substring
        int replaceCount;   // number of replacement chars needed in the sliding window for all the chars in the window to be same
        int[] charArray = new int[26];

        for (int right = 0; right < s.length(); right++) {
            charArray[s.charAt(right) - 'A']++;
            maxUniqueCharCount = Math.max(maxUniqueCharCount, charArray[s.charAt(right) - 'A']);

            replaceCount = (right - left + 1) - maxUniqueCharCount;
            if (replaceCount > k) {    // this means the sliding window has >k chars that are non unique, so this can't be the valid sliding window anymore, so adjust the window (increment the left and decrement its chat count accordingly)
                charArray[s.charAt(left) - 'A']--;
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        assertEquals(characterReplacement("ABAB", 2), 4);
        assertEquals(characterReplacement("AABABBA", 1), 4);
        assertEquals(characterReplacement("ABAA", 0), 2);
    }
}
