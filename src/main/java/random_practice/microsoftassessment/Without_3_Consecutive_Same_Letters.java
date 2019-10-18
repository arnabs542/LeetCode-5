package random_practice.microsoftassessment;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/17/19.
 * <p>
 * String Without 3 Identical Consecutive Letters
 * </p>
 */
public class Without_3_Consecutive_Same_Letters {
    private static String without3Consecutive(String input) {
        if (input == null || input.length() < 3) {
            return input;
        }

        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        char curr = input.charAt(0);

        for (char ch : input.toCharArray()) {
            stringBuilder.append(ch);
            if (ch == curr) {
                count++;   // increment the count when you encounter the same character
            } else {
                curr = ch;
                count = 1;  // reset the count when you encounter a new character
            }

            if (count >= 3) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);   // remove the last character when the count exceeds 3
            }
        }
        return stringBuilder.toString();
    }

    // alternate solution
    private static String without3Consecutive2(String input) {
        if (input == null || input.length() < 3) {
            return input;
        }

        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(input.charAt(0));   // handle the first letter separately as it doesn't have a prev character

        for (int i = 1; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == input.charAt(i - 1)) {
                count++;   // increment the count when you encounter the same character
            } else {
                count = 1;  // reset the count when you encounter a new character
            }

            if (count <= 2) {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        assertEquals(without3Consecutive("eedaaad"), "eedaad");
        assertEquals(without3Consecutive("xxxxtxxx"), "xxtxx");
        assertEquals(without3Consecutive("uuuuxaaaaxuuu"), "uuxaaxuu");

        assertEquals(without3Consecutive2("eedaaad"), "eedaad");
        assertEquals(without3Consecutive2("xxxxtxxx"), "xxtxx");
        assertEquals(without3Consecutive2("uuuuxaaaaxuuu"), "uuxaaxuu");
    }
}
