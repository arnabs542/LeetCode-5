package _5_Longest_Palindromic_Substring;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/29/19.
 * <p>
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * </p>
 */
public class _438_Find_All_Anagrams_String {
    private static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return result;
        }

        int left = 0, right = 0, count = p.length();
        int[] hash = new int[256];
        for (char ch : p.toCharArray()) {
            hash[ch]++;
        }

        while (right < s.length()) {
            if (hash[s.charAt(right)] >= 1) {
                count--;
            }
            hash[s.charAt(right)]--;
            right++;

            if (count == 0) {
                result.add(left);
            }

            if (right - left == p.length()) {
                if (hash[s.charAt(left)] >= 0) {
                    count++;
                }
                hash[s.charAt(left)]++;
                left++;
            }
        }
        return result;
    }

    // core logic: use 2 pointers (begin and end) to move the sliding windows and a counter to keep track of elements encountered
    // when counter < 0, it means we already encountered more than needed characters from the target substring, when counter > 0 , we need to encounter more characters from the target string, when target =0, we encountered exactly same number of characters
    // generic template idea taken from: https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
    private static List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return result;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character character : p.toCharArray()) {
            map.put(character, map.getOrDefault(character, 0) + 1);
        }

        int begin = 0, end = 0, counter = map.size();
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
                if (end - begin == p.length()) {
                    result.add(begin);
                }
                begin++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(findAnagrams("cbaebabacd", "abc"), Arrays.asList(0, 6));
        assertEquals(findAnagrams("abab", "ab"), Arrays.asList(0, 1, 2));

        // test method: 2
        assertEquals(findAnagrams2("cbaebabacd", "abc"), Arrays.asList(0, 6));
        assertEquals(findAnagrams2("abab", "ab"), Arrays.asList(0, 1, 2));
    }
}