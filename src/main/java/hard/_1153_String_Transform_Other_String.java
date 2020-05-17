package hard;

import org.testng.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by udaythota on 5/17/20.
 * <p>
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 * Return true if and only if you can transform str1 into str2.
 * </p>
 */
public class _1153_String_Transform_Other_String {
    // core logic: use a hash map to save the mappings of characters in first string to characters in second string. if we encounter a mapping with 2 diff states, then return false (eg: a in first string mapped to b in second string and then again a at diff position is mapped to c in diff position of second string)
    // the main condition is to check the values size in the end: this ensures we have a temporary swap variable which we can use to transform the non matching characters. eg: if string1 is has all 26 characters and string 2 has 26 characters, we cannot make the transformation as we will not have characters that we can use for transformations
    // TC: O(n), SC: O(n)
    private static boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            if (map.containsKey(str1.charAt(i)) && map.get(str1.charAt(i)) != str2.charAt(i)) {
                return false;
            }
            map.put(str1.charAt(i), str2.charAt(i));
        }
        return new HashSet<>(map.values()).size() < 26;   // to make sure we have at least one temporary character to transform the input string
    }

    public static void main(String[] args) {
        Assert.assertTrue(canConvert("aabcc", "ccdee"));
        Assert.assertFalse(canConvert("leetcode", "codeleet"));
    }
}
