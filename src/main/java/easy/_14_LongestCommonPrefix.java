package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 4/13/19.
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string ""
 * Input: ["flower","flow","flight"]  Output: "fl"
 * </p>
 */
public class _14_LongestCommonPrefix {
    // sort the strings lexicographically. take the shortest and longest string, compare the chars till we encounter a different character and return the longest common prefix
    // TC: O(n + m) -> where n is the number of strings and m is the length of the shortest string
    private static String longestCommonPrefix(String[] inputStrings) {
        String longestCommonPrefix = "";
        if (inputStrings != null && inputStrings.length != 0) {
            String smallest = inputStrings[0];
            String largest = inputStrings[0];

            // to get the smallest string (in terms of length) as the common prefix cannot be larger than that
            // sorting the array is O(nlogn), so just iterate through the string set to get the smallest and largest -> O(n)
            for (String string : inputStrings) {
                if (string.compareTo(smallest) < 0) {
                    smallest = string;
                }
                if (string.compareTo(largest) > 0) {
                    largest = string;
                }
            }

            // iterate through the smaller string, break the loop when the character mismatches between smaller and largest strings, calculate the longest prefix and return the result
            int i = 0;
            while (i < smallest.length() && smallest.charAt(i) == largest.charAt(i)) {
                i++;
            }
            longestCommonPrefix = smallest.substring(0, i);
        }
        return longestCommonPrefix;
    }

    // horizontal scan : accepted in leetcode (though the above solution is much easier)
    private static String longestCommonPrefixAlternate(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        assertEquals(longestCommonPrefix(new String[]{"flower", "flow", "flight"}), "fl");
        assertEquals(longestCommonPrefix(new String[]{"dog", "racecar", "car"}), "");
        assertEquals(longestCommonPrefix(new String[]{"a"}), "a");
        assertEquals(longestCommonPrefix(new String[]{"beehive", "application", "apex"}), "");

        assertEquals(longestCommonPrefixAlternate(new String[]{"flower", "flow", "flight"}), "fl");
        assertEquals(longestCommonPrefixAlternate(new String[]{"flower", "flow", "flight"}), "fl");
    }
}