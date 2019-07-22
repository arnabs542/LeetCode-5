package easy;

import org.testng.Assert;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 5/11/19.
 * <p>
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p/>
 */
public class _242_ValidAnagram {

    // TC: O(n) -> where n is the max length of the input strings
    // SC: ~O(1)
    private static boolean isAnagram(String string1, String string2) {
        // when length of 2 strings are unequal, they are never anagrams
        if (string1 == null || string2 == null || string1.length() != string2.length()) {
            return false;
        }

        int[] alphabetCountArray = new int[26];  // to keep the count of each character in the given input string
        for (int i = 0; i < string1.length(); i++) {
            alphabetCountArray[string1.charAt(i) - 'a']++;  // increment count for each character
            alphabetCountArray[string2.charAt(i) - 'a']--;  // // decrement count for each character
        }

        for (int i = 0; i < 26; i++) {
            if (alphabetCountArray[i] != 0) {  // if count of any character is not equal to zero, the given strings are not anagrams
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assertTrue(isAnagram("anagram", "nagaram"));
        assertFalse(isAnagram("rat", "car"));
        assertTrue(isAnagram("", ""));
    }
}
