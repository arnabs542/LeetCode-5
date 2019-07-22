package easy;

import java.util.Arrays;

/**
 * Created by udaythota on 7/15/19.
 * <p>
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * </p>
 */
public class _344_Reverse_String {
    // simple iterative approach: using 2 pointers
    private static void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;

        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    // recursive approach (modified the input from char array to string to make it more generic)
    private static String reverseString2(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String leftString = s.substring(0, s.length() / 2);
        String rightString = s.substring(s.length() / 2, s.length());
        return reverseString2(rightString) + reverseString2(leftString);
    }

    public static void main(String[] args) {
        // test method: 1
        char[] input = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverseString(input);
        System.out.println(Arrays.toString(input));

        // test method: 2
        char[] input2 = new char[]{'H', 'a', 'n', 'n', 'a', 'h'};
        reverseString(input2);
        System.out.println(Arrays.toString(input2));

        System.out.println(reverseString2("hello"));
    }
}