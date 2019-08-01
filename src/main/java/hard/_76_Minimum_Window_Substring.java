package hard;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/1/19.
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * </p>
 */
public class _76_Minimum_Window_Substring {
    // core logic: same as find all anagrams (LC: 438)
    // solution derived from generic sliding window template: https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
    private static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || t.length() > s.length()) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character character : t.toCharArray()) {
            map.put(character, map.getOrDefault(character, 0) + 1);
        }

        int begin = 0, end = 0, counter = map.size(), length = Integer.MAX_VALUE;
        int head = 0;
        while (end < s.length()) {
            char endChar = s.charAt(end);
            if (map.containsKey(endChar)) {
                map.put(endChar, map.get(endChar) - 1);
                if (map.get(endChar) == 0) {   // decrease the counter when we encounter a char completely from the target string (eg: if target = aa, we need to encounter 2 aa's in the input string before we decrement the counter)
                    counter--;
                }
            }
            end++;

            while (counter == 0) {   // you encountered all the needed characters from the target string: go ahead and check the condition to see if you can add it to the result
                char beginChar = s.charAt(begin);
                if (map.containsKey(beginChar)) {
                    map.put(beginChar, map.get(beginChar) + 1);   // as you move begin, increment their values as well as they need to searched again in the new sliding window
                    if (map.get(beginChar) > 0) {  // this means we need to search the characters again
                        counter++;
                    }
                }
                if (end - begin < length) {  // update the head and length when you encounter a new smaller string
                    length = end - begin;
                    head = begin;
                }
                begin++;
            }
        }
        if (length == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(head, head + length);
    }

    public static void main(String[] args) {
        assertEquals(minWindow("ADOBECODEBANC", "ABC"), "BANC");
    }
}