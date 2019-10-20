package _5_Longest_Palindromic_Substring;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/26/19.
 * <p>
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * </p>
 */
public class _165_Compare_Version_Numbers {
    // core logic: split by levels / sub versions (using '.') and compare each level by fetching the absolute level value
    private static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null || version1.length() == 0 || version2.length() == 0) {
            return -1;
        }
        String[] input1 = version1.split("\\.");   // splits by '.'  (make sure to have a right escape character for '.')
        String[] input2 = version2.split("\\.");

        int length1 = input1.length;
        int length2 = input2.length;
        int maxLength = Math.max(length1, length2);

        for (int i = 0; i < maxLength; i++) {
            int v1 = i < length1 ? Integer.parseInt(input1[i]) : 0;  // assume version as zero when its greater than length (default)
            int v2 = i < length2 ? Integer.parseInt(input2[i]) : 0;

            if (v1 > v2) {
                return 1;
            }
            if (v1 < v2) {
                return -1;
            }
        }
        return 0;   // both are equal
    }

    // similar approach as above
    private static int compareVersion2(String version1, String version2) {
        if (version1 == null || version2 == null || version1.length() == 0 || version2.length() == 0) {
            return -1;
        }
        int length1 = version1.length();
        int length2 = version2.length();

        for (int i = 0, j = 0; i < length1 || j < length2; i++, j++) {
            int v1 = 0;
            int v2 = 0;

            while (i < length1 && version1.charAt(i) != '.') {
                v1 = v1 * 10 + version1.charAt(i) - '0';
                i++;
            }

            while (j < length2 && version2.charAt(j) != '.') {
                v2 = v2 * 10 + version2.charAt(j) - '0';
                j++;
            }

            if (v1 > v2) {
                return 1;
            }
            if (v1 < v2) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(compareVersion("0.1", "1.1"), -1);
        assertEquals(compareVersion("1.0.1", "1"), 1);
        assertEquals(compareVersion("7.5.2.4", "7.5.3"), -1);
        assertEquals(compareVersion("1.01", "1.001"), 0);
        assertEquals(compareVersion("1.0", "1.0.0"), 0);
        assertEquals(compareVersion("1.101", "1.100001"), -1);

        // test method: 2
        assertEquals(compareVersion2("0.1", "1.1"), -1);
        assertEquals(compareVersion2("1.0.1", "1"), 1);
        assertEquals(compareVersion2("7.5.2.4", "7.5.3"), -1);
        assertEquals(compareVersion2("1.01", "1.001"), 0);
        assertEquals(compareVersion2("1.0", "1.0.0"), 0);
        assertEquals(compareVersion2("1.1", "1.00001"), 0);
        assertEquals(compareVersion2("1.101", "1.100001"), -1);
    }
}