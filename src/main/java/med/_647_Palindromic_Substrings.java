package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/20/20.
 * <p>
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * </p>
 */
public class _647_Palindromic_Substrings {

    private int count = 0;

    // core logic: just an extension of the longest palindrome question. as here any sub string which is palindrome can be part of the result, we check for all those combinations and keep adding them when they are valid
    // we need to check 2 cases (with i as center and i, i+1 as centres, as with even characters, the palindromes can be found only with i, i+1 as the center)
    // TC: O(N^2)
    private int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            isPalindrome(s, i, i);  // to take care of odd lengths
            isPalindrome(s, i, i + 1);  // to take care of even lengths
        }
        return count;
    }

    // as any sub string which is a palindrome can be part of the result, we need to check for all the sub strings with centres as palindromes
    private void isPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++)) {
            count++;    // this means the sub string is palindrome. so keep continuing
        }
    }

    public static void main(String[] args) {
        _647_Palindromic_Substrings palindromic_substrings = new _647_Palindromic_Substrings();
        Assert.assertEquals(palindromic_substrings.countSubstrings("abc"), 3);
        Assert.assertEquals(palindromic_substrings.countSubstrings("aaa"), 6);
    }
}
