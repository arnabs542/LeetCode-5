package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/25/19.
 * <p>
 * Given an input string, reverse the string word by word.
 * TODO: this could be done with reverse function as well (reverse the whole string + reverse each word + remove additional spaces)
 * </p>
 */
public class _151_Reverse_Words_String {
    // core logic: process the sub strings without spaces and add the spaces to string builder whenever needed
    private static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        s = s.trim();

        int end = s.length();
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) == ' ') {
                stringBuilder.append(s.substring(i + 1, end)).append(' ');
                while (s.charAt(i - 1) == ' ') {
                    i--;
                }
                end = i;
            }
        }
        stringBuilder.append(s.substring(0, end));
        return stringBuilder.toString();
    }

    /*private static void reverse(char[] input, int start, int end) {
        while (start < end) {
            char temp = input[start];
            input[start++] = input[end];
            input[end] = temp;
        }
    }*/

    public static void main(String[] args) {
        assertEquals(reverseWords("the sky is blue"), "blue is sky the");
        assertEquals(reverseWords("  hello world!  "), "world! hello");
        assertEquals(reverseWords("a good   example"), "example good a");
    }
}