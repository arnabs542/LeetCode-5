package easy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 6/29/19.
 * <p>
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * </p>
 */
public class _125_ValidPalindrome {

    private static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }

        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            while (start <= end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            while (end >= 0 && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }

            if (start < end && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // same as method 1: just a little different style of writing code
    private static boolean isPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }

        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else {
                if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }

    private static boolean isPalindrome3(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static void main(String[] args) {
        // test method: 1
        assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(isPalindrome("race a car"));

        // test method: 2
        assertTrue(isPalindrome2("A man, a plan, a canal: Panama"));
        assertFalse(isPalindrome2("race a car"));

        // test method: 3
        assertTrue(isPalindrome3("A man, a plan, a canal: Panama"));
        assertFalse(isPalindrome3("race a car"));
    }
}
