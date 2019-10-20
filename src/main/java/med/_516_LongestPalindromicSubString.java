package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 1/18/19.
 * TODO: this is wrong. redo it
 */
public class _516_LongestPalindromicSubString {

    private static String longestPalindrome(String testString) {

        if (testString == null || testString.length() <= 1 || testString.equals("")) {
            return testString;
        }

        int startPointer = 0;
        int endPointer = testString.length() - 1;
        int resultStart = 0, resultEnd = 0;

        while (startPointer <= endPointer) {
            if (testString.charAt(startPointer) == testString.charAt(endPointer)) {
                resultStart = Math.min(startPointer, resultStart);
                resultEnd = Math.max(endPointer, resultEnd);
            } else {
                resultStart = resultEnd = 0;
            }
            startPointer++;
            endPointer--;
        }

        String finalString = testString.substring(resultStart, resultEnd + 1);
        if (finalString.charAt(0) != finalString.charAt(finalString.length() - 1)) {
            return finalString.substring(1, finalString.length());
        } else {
            return finalString;
        }

        // if(testString.substring(resultStart, resultEnd).charAt(0)!=testString.substring(resultStart, resultEnd).charAt()
        // return testString.substring(resultStart, resultEnd + 1);
    }

    public static void main(String[] args) {
        // assertEquals(longestPalindrome("babad"), "bab");
        //  assertEquals(longestPalindrome("abaaba"), "abaaba");
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("abaaba"));
        System.out.println(longestPalindrome("madam"));
        System.out.println(longestPalindrome("abb"));
    }
}
