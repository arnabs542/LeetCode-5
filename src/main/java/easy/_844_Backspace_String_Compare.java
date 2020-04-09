package easy;

import org.testng.Assert;

import java.util.Stack;

/**
 * Created by udaythota on 4/9/20.
 * <p>
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * </p>
 */
public class _844_Backspace_String_Compare {
    // naive / brute force solution.
    // TC: O(m+n), SC: O(m+n) -> where m and n are the lengths of two strings
    private static boolean backspaceCompare(String S, String T) {
        String res1 = backSpaceCompareHelper(S);
        String res2 = backSpaceCompareHelper(T);
        return res1.equals(res2);
    }

    // simple helper function
    private static String backSpaceCompareHelper(String string) {
        Stack<Character> stack = new Stack<>();
        for (Character ch : string.toCharArray()) {
            if (ch == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }
        return stack.toString();
    }

    // simple 2 pointer solution: scan the string from right to left, and whenever you encounter a char other than '#', compare both of them and make sure they are equal
    // when you encounter '#', keep the count of them and skip processing those many number of chars (as those will not be present in the actual string)
    // TC: O(m+n), SC: O(1)
    private static boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;

        int backS = 0, backT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    backS++;   // keep the count of '#'
                } else {
                    if (backS > 0) {
                        backS--;  // skip those many chars with count of '#'
                    } else {
                        break;   // encountered the valid character in S
                    }
                }
                i--;
            }

            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    backT++;
                } else {
                    if (backT > 0) {
                        backT--;
                    } else {
                        break;   // encountered the valid character in T
                    }
                }
                j--;
            }

            // compare the valid characters: when one of the indexes reaches the start early (<0), that means both the strings cannot be equal. when both of them are >0, make sure the chars at those those positions are equal
            if ((i < 0 && j >= 0) || (i >= 0 && j < 0) || (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))) {
                return false;
            } else {
                i--;
                j--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // solution 1
        Assert.assertTrue(backspaceCompare("ab#c", "ad#c"));
        Assert.assertTrue(backspaceCompare("ab##", "c#d#"));
        Assert.assertTrue(backspaceCompare("a##c", "#a#c"));
        Assert.assertFalse(backspaceCompare("a#c", "b"));
        Assert.assertFalse(backspaceCompare("bbbextm", "bbb#extm"));

        // solution 2
        Assert.assertTrue(backspaceCompare2("ab#c", "ad#c"));
        Assert.assertTrue(backspaceCompare2("ab##", "c#d#"));
        Assert.assertTrue(backspaceCompare2("a##c", "#a#c"));
        Assert.assertFalse(backspaceCompare2("a#c", "b"));
        Assert.assertFalse(backspaceCompare2("bbbextm", "bbb#extm"));
    }
}
