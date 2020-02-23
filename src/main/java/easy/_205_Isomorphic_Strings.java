package easy;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 2/22/20.
 * <p>
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 * </p>
 */
public class _205_Isomorphic_Strings {
    private static boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> characterStoreHashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character characterInFirstString = s.charAt(i);
            Character characterInSecondString = t.charAt(i);

            if (characterStoreHashMap.containsKey(characterInFirstString)) {
                Character firstCharValue = characterStoreHashMap.get(characterInFirstString);
                if (firstCharValue != characterInSecondString) {
                    return false;
                }
            } else {
                if (characterStoreHashMap.containsValue(characterInSecondString)) {     // IMPORTANT: c2 is already mapped, so return false
                    return false;
                }
                characterStoreHashMap.put(characterInFirstString, characterInSecondString);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assertTrue(isIsomorphic("egg", "add"));
        assertFalse(isIsomorphic("foo", "bar"));
        assertTrue(isIsomorphic("paper", "title"));
    }
}