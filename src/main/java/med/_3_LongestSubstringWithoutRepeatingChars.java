package med;

import java.util.HashMap;

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

    // similar to above approach: sliding window algorithm (used template: https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.)
    // see the 1st solution though for the optimized approach
    private static int lengthOfLongestSubstring2(String inputString) {
        if (inputString == null || inputString.length() == 0) {
            return 0;
        }

        int begin = 0, end = 0, counter = 0, result = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (end < inputString.length()) {
            char ch = inputString.charAt(end);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.get(ch) > 1) {
                counter++;
            }
            end++;
            while (counter > 0) {
                char beginChar = inputString.charAt(begin);
                if (map.get(beginChar) > 1) {
                    counter--;
                }
                map.put(beginChar, map.get(beginChar) - 1);
                begin++;
            }
            result = Math.max(result, end - begin);
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(lengthOfLongestSubstring("aabcadeaabf"), 5);
        assertEquals(lengthOfLongestSubstring("bbbbb"), 1);
        assertEquals(lengthOfLongestSubstring("abcdea"), 5);
        assertEquals(lengthOfLongestSubstring("abcabcbb"), 3);
        assertEquals(lengthOfLongestSubstring("aabdceb"), 5);
        assertEquals(lengthOfLongestSubstring("au"), 2);
        assertEquals(lengthOfLongestSubstring("aab"), 2);

        assertEquals(lengthOfLongestSubstring2("aabcadeaabf"), 5);
        assertEquals(lengthOfLongestSubstring2("bbbbb"), 1);
        assertEquals(lengthOfLongestSubstring2("abcdea"), 5);
        assertEquals(lengthOfLongestSubstring2("abcabcbb"), 3);
        assertEquals(lengthOfLongestSubstring2("aabdceb"), 5);
        assertEquals(lengthOfLongestSubstring2("au"), 2);
        assertEquals(lengthOfLongestSubstring2("aab"), 2);
    }
}