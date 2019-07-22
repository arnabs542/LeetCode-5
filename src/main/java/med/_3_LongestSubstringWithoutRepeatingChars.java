package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/25/19.
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * </p>
 */
public class _3_LongestSubstringWithoutRepeatingChars {

    // NOTE: take care of the last case: if the loop exits without a repeating character, max length should be explicitly calculated
    // Time Complexity: O(n^1), Space Complexity: O(1): as char array space is always a constant
    private static int lengthOfLongestSubstring(String inputString) {

        if (inputString == null || inputString.equals("") || inputString.length() < 1) {
            return 0;
        }

        int maxLength = 1;
        int startPointer = 0;
        boolean[] charArray = new boolean[256];  // to take care of all the characters

        for (int i = 0; i < inputString.length(); i++) {
            if (charArray[inputString.charAt(i)]) {
                char currentChar = inputString.charAt(i);
                int currentLength = i - startPointer;

                for (int j = startPointer; j < i; j++) {
                    if (inputString.charAt(j) == currentChar) {
                        startPointer = j + 1;
                        break;
                    }
                }
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
            } else {
                charArray[inputString.charAt(i)] = true;
            }
        }

        // NOTE: last case: when the loop exits, calculate the current length and reassign the max length if needed
        maxLength = Math.max(maxLength, inputString.length() - startPointer);
        return maxLength;
    }

    public static void main(String[] args) {
        assertEquals(lengthOfLongestSubstring("aabcadeaabf"), 5);
        assertEquals(lengthOfLongestSubstring("bbbbb"), 1);
        assertEquals(lengthOfLongestSubstring("abcdea"), 5);
        assertEquals(lengthOfLongestSubstring("abcabcbb"), 3);
        assertEquals(lengthOfLongestSubstring("aabdceb"), 5);
        assertEquals(lengthOfLongestSubstring("au"), 2);
        assertEquals(lengthOfLongestSubstring("aab"), 2);
    }
}
