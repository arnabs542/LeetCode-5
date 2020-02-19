package med;

import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/18/20.
 * <p>
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * </p>
 */
public class _394_Decode_String {
    // core logic: use 2 stacks, one to maintain the counts and other to maintain the pattern strings. based on char type do the appropriate calculations
    private static String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Stack<Integer> countStack = new Stack<>();
        Stack<String> patternStack = new Stack<>();
        int i = 0;
        String result = "";

        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                countStack.push(num);
            } else if (s.charAt(i) == '[') {
                patternStack.push(result);
                result = "";
                i++;
            } else if (s.charAt(i) == ']') {
                StringBuilder stringBuilder = new StringBuilder(patternStack.pop());
                int count = countStack.pop();
                for (int j = 0; j < count; j++) {
                    stringBuilder.append(result);
                }
                result = stringBuilder.toString();
                i++;
            } else {
                result += s.charAt(i);
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(decodeString("3[a]2[bc]"), "aaabcbc");
        assertEquals(decodeString("3[a2[c]]"), "accaccacc");
        assertEquals(decodeString("2[abc]3[cd]ef"), "abcabccdcdcdef");
    }
}