package med;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 10/26/19.
 * <p>
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 * </p>
 */
public class _567_Permutations_In_String {
    // same as the sliding window pattern. apply the filter conditions carefully
    private static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() < s1.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (Character ch : s1.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int left = 0, right = 0, counter = map.size();
        while (right < s2.length()) {
            char rightChar = s2.charAt(right);
            if (map.containsKey(rightChar)) {
                map.put(rightChar, map.get(rightChar) - 1);
                if (map.get(rightChar) == 0) {
                    counter--;
                }
            }
            right++;

            while (counter == 0) {   // this means sliding window has all the characters needed from the target string
                char beginChar = s2.charAt(left);
                if (map.containsKey(beginChar)) {
                    map.put(beginChar, map.get(beginChar) + 1);   // reset the char count
                    if (map.get(beginChar) > 0) {   // this means sliding window no more contains all the valid characters, so increment the counter and thereafter move the sliding window
                        counter++;
                    }
                }

                if (right - left == s1.length()) {
                    return true;
                }
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assertTrue(checkInclusion("ab", "eidbaooo"));
        assertFalse(checkInclusion("ab", "eidboaoo"));
    }
}