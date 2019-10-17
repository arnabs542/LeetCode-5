package random_practice.microsoftassessment;

import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/16/19.
 * <p>
 * Lexicographically smallest string formed by removing at most one character.
 * </p>
 */
public class Lexographically_Smallest_String {
    private static String smallestString(String input) {
        if (input == null || input.length() <= 1) {
            return null;
        }

        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            if (!stack.isEmpty() && count < 1 && input.charAt(i) < stack.peek()) {   // count = k, where k is the number of chars that can be removed (here it is 1)
                stack.pop();
                count++;
            }
            stack.push(input.charAt(i));
        }

        if (count == 0) {  // this means all chars in the string are in the ascending order. so just pop the last character, as removing that will be the smallest
            stack.pop();
        }
        while (!stack.isEmpty()) {
            stringBuilder.insert(0, stack.pop());  // as stack contains elements in reverse order, reverse the string by inserting in the beginning
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        assertEquals(smallestString("abczd"), "abcd");
    }
}
