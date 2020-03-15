package hard;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 3/14/20.
 * <p>
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * </p>
 */
public class _97_Interleaving_Strings {
    // DFS / recursive approach: similar to merge sort: increment the pointers in p1, p2, p3 as appropriate based on the character matches
    // TC: O(2^m+n) -> as we are basically checking for all the interleaving combinations in both the strings
    // SC: O(m + n) -> size of stack
    private static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return dfs(0, 0, 0, s1, s2, s3);
    }

    private static boolean dfs(int p1, int p2, int p3, String s1, String s2, String s3) {
        if (p3 == s3.length() && p1 == s1.length() && p2 == s2.length()) {
            return true;
        }
        if (p1 == s1.length()) {
            return s2.substring(p2).equals(s3.substring(p3));
        }
        if (p2 == s2.length()) {
            return s1.substring(p1).equals(s3.substring(p3));
        }

        boolean result = false;
        if (s1.charAt(p1) == s3.charAt(p3)) {
            result = dfs(p1 + 1, p2, p3 + 1, s1, s2, s3);
            if (result) {
                return result;
            }
        }
        if (s2.charAt(p2) == s3.charAt(p3)) {
            result = dfs(p1, p2 + 1, p3 + 1, s1, s2, s3);
            if (result) {
                return result;
            }
        }
        return result;
    }

    // DFS with memoization: exact same as above approach, except that we use map here to memoize the current seen interleaving combinations so we don't repeat them again (in order to reduce the time)
    // TC: O(m * n), SC: O(m * n)
    // TODO: we can also solve this problem using the DP approach, but the time complexity would still be the same
    private static boolean isInterleave2(String s1, String s2, String s3) {
        HashMap<String, Boolean> map = new HashMap<>();
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return dfs2(0, 0, 0, s1, s2, s3, map);
    }

    private static boolean dfs2(int p1, int p2, int p3, String s1, String s2, String s3, HashMap<String, Boolean> map) {
        if (p3 == s3.length() && p1 == s1.length() && p2 == s2.length()) {
            return true;
        }
        if (p1 == s1.length()) {
            return s2.substring(p2).equals(s3.substring(p3));
        }
        if (p2 == s2.length()) {
            return s1.substring(p1).equals(s3.substring(p3));
        }

        String key = p1 + " " + p2 + " " + p3;   // as this combination will be unique for all the string interleaving combinations, use this as a key
        if (map.containsKey(key)) {   // if this key is present, we already checked for this combination, so just return the value
            return map.get(key);
        }

        boolean result = false;
        if (s1.charAt(p1) == s3.charAt(p3)) {
            result = dfs2(p1 + 1, p2, p3 + 1, s1, s2, s3, map);
            if (result) {
                return result;
            }
        }
        if (s2.charAt(p2) == s3.charAt(p3)) {
            result = dfs2(p1, p2 + 1, p3 + 1, s1, s2, s3, map);
            if (result) {
                return result;
            }
        }
        map.put(key, result);   // save the result for the current interleaving combinations
        return result;
    }

    public static void main(String[] args) {
        assertTrue(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        assertFalse(isInterleave("aabcc", "dbbca", "aadbbbaccc"));

        assertTrue(isInterleave2("aabcc", "dbbca", "aadbbcbcac"));
        assertFalse(isInterleave2("aabcc", "dbbca", "aadbbbaccc"));
    }
}