package easy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 8/17/19.
 * <p>
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * </p>
 */
public class _953_Verifying_Alien_Dictionary {
    private static int[] positions = new int[26];

    // core logic: create the positions map which stores the order of given characters (based on the given ORDER STRING) - which can later be used to look up the position of characters as per the new dictionary order
    // start from beginning and compare every 2 words if the order is valid. if not, return false, else return true
    private static boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < 26; i++) {
            positions[order.charAt(i) - 'a'] = i;     // look up map to get the position of character based on the given order
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!isOrdered(words[i], words[i + 1])) {
                return false;
            }
        }
        return true;
    }

    // helper method to check if the given 2 strings are lexicographic based on the given order
    private static boolean isOrdered(String word1, String word2) {
        int i = 0, j = 0;
        while (i < word1.length()) {
            if (j == word2.length()) {  // 2nd word reached to end: which means means same characters and smaller string. so return false (smaller string with same characters should come first in the lexicographic order)
                return false;
            }
            if (positions[word1.charAt(i) - 'a'] < positions[word2.charAt(j) - 'a']) {
                return true;
            } else if (positions[word1.charAt(i) - 'a'] > positions[word2.charAt(i) - 'a']) {   // order violated
                return false;
            } else {    // same characters in both words, so increment both the pointers
                i++;
                j++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assertTrue(isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        assertFalse(isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
        assertFalse(isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }
}