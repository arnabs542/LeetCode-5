package easy;

import java.util.HashSet;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 2/16/20.
 * <p>
 * Given a string, determine if a permutation of the string could form a palindrome.
 * </p>
 */
public class _266_Palindrome_Permutation {
    // core logic: for any palindrome to be valid the utmost number of odd characters in the string possible could only be 1. for even length strings, the odd char count will be zero while for odd length strings, the odd char count will be 1
    // TC: O(n)
    private static boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        int oddCountChars = 0;
        int[] charArray = new int[256];
        for (int i = 0; i < s.length(); i++) {
            charArray[s.charAt(i)]++;
        }

        for (int i = 0; i < 256; i++) {
            if (charArray[i] % 2 != 0) {   // count the odd count chars
                oddCountChars++;
            }
        }
        return oddCountChars <= 1;  // odd chars should should be utmost 1 to be valid palindrome
    }

    // same as above logic but uses set to keep the count of odd count characters in the string   
    private static boolean canPermutePalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        HashSet<Character> set = new HashSet<>();
        for (Character ch : s.toCharArray()) {
            if (set.contains(ch)) {
                set.remove(ch);
            } else {
                set.add(ch);
            }
        }
        return set.size() <= 1;
    }

    public static void main(String[] args) {
        assertTrue(canPermutePalindrome("aab"));
        assertFalse(canPermutePalindrome("code"));
        assertTrue(canPermutePalindrome("carerac"));

        assertTrue(canPermutePalindrome2("aab"));
        assertFalse(canPermutePalindrome2("code"));
        assertTrue(canPermutePalindrome2("carerac"));
    }
}