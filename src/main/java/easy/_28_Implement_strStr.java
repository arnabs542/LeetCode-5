package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/23/19.
 * <p>
 * Implement strStr().
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * </p>
 */
public class _28_Implement_strStr {

    // TC: O(n)
    private static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        int hayLength = haystack.length();
        int needleLength = needle.length();
        for (int i = 0; i < hayLength - needleLength + 1; i++) {    // iterate only till hayLength - needleLength + 1
            int count = 0;
            while (count < needleLength && haystack.charAt(i + count) == needle.charAt(count)) {
                count++;
            }
            if (count == needleLength) {   // this means we found the needle
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assertEquals(strStr("hello", "ll"), 2);
        assertEquals(strStr("aaaaa", "bba"), -1);
        assertEquals(strStr("aaa", "aaaa"), -1);
        assertEquals(strStr("aaaa", "aaa"), 0);
        assertEquals(strStr("mississippi", "issi"), 1);
        assertEquals(strStr("", ""), 0);
        assertEquals(strStr("", "a"), -1);
    }
}
