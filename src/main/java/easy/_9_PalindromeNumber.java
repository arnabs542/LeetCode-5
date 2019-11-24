package easy;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by udaythota on 4/17/19.
 * <p>
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * Input: 121 Output: true
 * Input: -121 Output: false Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * </p>
 */
public class _9_PalindromeNumber {
    // see the below solution for the most optimal implementation
    private static boolean isPalindrome(int inputNumber) {
        if (inputNumber < 0) {
            return false;
        }

        int input = inputNumber;
        long reverse = 0;     // NOTE: see the solution below without using a long
        while (inputNumber != 0) {
            reverse = (reverse * 10) + (inputNumber % 10);
            inputNumber = inputNumber / 10;
        }
        return reverse == input;
    }

    // the key is that here, we only check for half the length of input number
    private static boolean isPalindromeWithoutUsingLong(int inputNumber) {
        if (inputNumber < 0 || (inputNumber != 0 && inputNumber % 10 == 0)) {   // when number is exactly divisible by 0, it is never a palindrome
            return false;
        }

        int reverse = 0;
        while (inputNumber > reverse) {
            reverse = (reverse * 10) + (inputNumber % 10);
            inputNumber = inputNumber / 10;
        }
        return (inputNumber == reverse || inputNumber == reverse / 10);   // first condition: when length of input number is even. second condition: when length of input number is odd
    }

    public static void main(String[] args) {
        assertFalse(isPalindrome(-121));
        assertTrue(isPalindrome(121));
        assertFalse(isPalindrome(10));
        assertTrue(isPalindrome(123321));

        assertFalse(isPalindromeWithoutUsingLong(-121));
        assertTrue(isPalindromeWithoutUsingLong(121));
        assertFalse(isPalindromeWithoutUsingLong(10));
        assertTrue(isPalindromeWithoutUsingLong(123321));
    }
}