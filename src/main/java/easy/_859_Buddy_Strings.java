package easy;

import java.util.HashSet;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by udaythota on 2/13/20.
 * <p>
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 * </p>
 */
public class _859_Buddy_Strings {
    private static boolean buddyStrings(String string1, String string2) {
        // core logic: case 1: when length of both the strings are unequal, return false
        // case 2: when both strings are equal, check if there are any duplicates, and if yes, return true (which means we can just swap duplicate chars and the string still remains the same), else return false
        // case 3: for all other cases, find the total un matching chars and their indexes and compare the un matching indexes in both the strings can make a possible swap
        if (string1 == null || string1.length() == 0 || string2 == null || string2.length() == 0 || string1.length() != string2.length()) {  // case 1
            return false;
        }

        int index1 = -1, index2 = -1, unMatchCount = 0;
        HashSet<Character> set = new HashSet<>();
        if (string1.equals(string2)) {    //  case 2
            for (Character ch : string1.toCharArray()) {
                set.add(ch);
            }
            return set.size() < string1.length();
        }

        for (int i = 0; i < string1.length(); i++) {    // case 3
            if (string1.charAt(i) != string2.charAt(i)) {
                unMatchCount++;
                if (index1 == -1) {
                    index1 = i;
                } else {
                    index2 = i;
                }
            }
        }
        return unMatchCount == 2 && string1.charAt(index1) == string2.charAt(index2) && string1.charAt(index2) == string2.charAt(index1);  // case 3
    }

    public static void main(String[] args) {
        assertTrue(buddyStrings("ab", "ba"));
        assertFalse(buddyStrings("ab", "ab"));
        assertTrue(buddyStrings("aa", "aa"));
        assertTrue(buddyStrings("aaaaaaabc", "aaaaaaacb"));
        assertFalse(buddyStrings("", "aa"));
    }
}