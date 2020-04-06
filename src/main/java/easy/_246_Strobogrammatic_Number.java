package easy;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 4/5/20.
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * </p>
 */
public class _246_Strobogrammatic_Number {
    // core logic: 2 pointers: have a map of all the strobgrammatic numbers, navigate the pointers and check if it is valid
    // TC: O(n), SC: O(n)
    private static boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        int i = 0, j = num.length() - 1;
        while (i <= j) {
            if (!map.containsKey(num.charAt(i)) || map.get(num.charAt(i)) != num.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        assertTrue(isStrobogrammatic("69"));
        assertTrue(isStrobogrammatic("88"));
        assertFalse(isStrobogrammatic("962"));
    }
}
