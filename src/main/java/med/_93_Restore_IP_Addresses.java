package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/24/19.
 * <p>
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * TODO: try solving this through backtracking using DFS
 * </p>
 */
public class _93_Restore_IP_Addresses {

    // core logic: divide the string in to 4 parts, iterate through all the possible sub strings and for all the valid sub strings, add to the result
    // TC: O(n), SC: O(1)
    private static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4) {
            return result;
        }

        int length = s.length();
        for (int i = 1; i < 4 && i < length - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < length - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < length; k++) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, length);

                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        result.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return result;
    }

    // simple utility function to check if each substring (one of the 4 sub ips) is valid or not
    // length of a valid string should be 1 and 3 and the int value of the string should be < 255
    // when first char of the string is '0', then the length of the string SHOULD NOT be greater than 1 (as 255.255.255.001 is an invalid ip, it should either be 255.255.255.0 or 255.255.255.1)
    private static boolean isValid(String string) {
        if (string.length() == 0 || string.length() > 3 || (string.charAt(0) == '0' && string.length() > 1) || Integer.parseInt(string) > 255) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assertEquals(restoreIpAddresses("25525511135"), Arrays.asList("255.255.11.135", "255.255.111.35"));
    }
}