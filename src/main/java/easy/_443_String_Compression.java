package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 11/7/19.
 * <p>
 * Given an array of characters, compress it in-place.
 * The length after compression must always be smaller than or equal to the original array.
 * Every element of the array should be a character (not int) of length 1.
 * </p>
 */
public class _443_String_Compression {
    // iterate through the input array and handle the case separately when there is a repetitive char encountered (record the count and use the count to populate the char array appropriately)
    // TC: O(n)
    private static int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int index = 0, i = 0;

        while (i < chars.length) {
            chars[index++] = chars[i++];
            int count = 1;
            while (i < chars.length && chars[i] == chars[i - 1]) {
                count++;
                i++;
            }
            if (count > 1) {
                for (char ch : String.valueOf(count).toCharArray()) {
                    chars[index++] = ch;
                }
            }
        }
        return index;
    }

    // this will only return the index, but the solution also asks to modify the input array. so DON'T use this solution
    private static int compress2(char[] chars) {
        if (chars == null) {
            return 0;
        }
        if (chars.length <= 1) {
            return chars.length;
        }

        int result = 0;
        int repeatSize = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                result++;
            } else {
                result++;
                while (i < chars.length && chars[i] == chars[i - 1]) {
                    i++;
                    repeatSize++;
                }
                result += String.valueOf(repeatSize).length();
                repeatSize = 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c'}), 6);
        assertEquals(compress(new char[]{'a'}), 1);
        assertEquals(compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}), 4);
    }
}