package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/19/19.
 * <p>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * </p>
 */
public class _5_Longest_Palindromic_Substring {
    // core logic: with one character and 2 characters as center, find the palindrome for each case, and whenever you encounter the longest palindrome, update it
    // two cases are needed to handle the odd and even cases separately
    private static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1 || s.equals("")) {
            return s;
        }

        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String temp = palindromeHelper(s, i, i);  // odd length
            if (temp.length() > longest.length()) {
                longest = temp;
            }

            temp = palindromeHelper(s, i, i + 1);  // even length
            if (temp.length() > longest.length()) {
                longest = temp;
            }
        }
        return longest;
    }

    // given a string and the CENTER(S) of the palindrome (i and i+1), find the longest possible palindrome
    // from center, go left and right till characters are same and return the longest palindromic substring
    private static String palindromeHelper(String string, int begin, int end) {
        while (begin >= 0 && end < string.length() && string.charAt(begin) == string.charAt(end)) {
            begin--;
            end++;
        }
        return string.substring(begin + 1, end);
    }

    public static void main(String[] args) {
        assertEquals(longestPalindrome("babad"), "bab");
        assertEquals(longestPalindrome("cbbd"), "bb");
    }
}