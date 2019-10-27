package random_practice.microsoftassessment;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/26/19.
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 */
public class Min_Window_Substring {
    private static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() > s.length()) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (Character ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int begin = 0, end = 0, count = map.size(), minLength = Integer.MAX_VALUE;
        String result = "";

        while (end < s.length()) {
            char endChar = s.charAt(end);
            if (map.containsKey(endChar)) {
                map.put(endChar, map.get(endChar) - 1);
                if (map.get(endChar) == 0) {
                    count--;
                }
            }
            end++;

            while (count == 0) {
                char beginChar = s.charAt(begin);
                if (map.containsKey(beginChar)) {
                    map.put(beginChar, map.get(beginChar) + 1);

                    if (map.get(beginChar) > 0) {
                        count++;
                    }
                }

                if (end - begin < minLength) {
                    minLength = end - begin;
                    result = s.substring(begin, end);
                }
                begin++;
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            result = "";
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(minWindow("ADOBECODEBANC", "ABC"), "BANC");
        assertEquals(minWindow("ABBADACKLBC", "ABC"), "BADAC");
    }
}