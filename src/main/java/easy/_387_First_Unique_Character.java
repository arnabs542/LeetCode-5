package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/17/19.
 * <p>
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * </p>
 */
public class _387_First_Unique_Character {

    // core logic: iterate through the string and increment the index of characters accordingly. iterate through the string again and return the first char with count = 1
    private static int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int[] charArray = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charArray[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (charArray[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assertEquals(firstUniqChar("leetcode"), 0);
        assertEquals(firstUniqChar("loveleetcode"), 2);
    }
}