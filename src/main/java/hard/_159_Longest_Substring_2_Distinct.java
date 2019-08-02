package hard;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/1/19.
 * <p>
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 * </p>
 */
public class _159_Longest_Substring_2_Distinct {
    // core logic: use 2 pointers (begin and end) to move the sliding windows and a counter to keep track of elements encountered
    // counter is used to track the unique elements encountered in each sliding window
    // generic template idea taken from: https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
    // TC: O(n) as start and end pointers move only once from begin to end
    private static int lengthOfLongestSubstringTwoDistinct(String s) {
        int length = 0;
        if (s == null || s.length() == 0) {
            return length;
        }

        int begin = 0, end = 0, count = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char endChar = s.charAt(end);
            map.put(endChar, map.getOrDefault(endChar, 0) + 1);
            if (map.get(endChar) == 1) {
                count++;
            }
            end++;

            while (count > 2) {   // this means the number of distinct elements encountered is > 2, so move begin to right till count == 2   (for k distinct, just replace 2 with k)
                char beginChar = s.charAt(begin);
                map.put(beginChar, map.get(beginChar) - 1);
                if (map.get(beginChar) == 0) {
                    count--;
                }
                begin++;
            }
            length = Math.max(length, end - begin);   // whenever count <=2, update the length when needed
        }
        return length;
    }

    public static void main(String[] args) {
        assertEquals(lengthOfLongestSubstringTwoDistinct("eceba"), 3);
        assertEquals(lengthOfLongestSubstringTwoDistinct("aabbb"), 5);
    }
}