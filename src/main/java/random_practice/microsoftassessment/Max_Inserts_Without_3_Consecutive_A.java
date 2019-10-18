package random_practice.microsoftassessment;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/17/19.
 * <p>
 * Max Inserts to Obtain String Without 3 Consecutive 'a'
 * </p>
 */
public class Max_Inserts_Without_3_Consecutive_A {
    // core logic: for each and every character in the input, try to put number of a's needed on its LEFT and in the end, add a's after the end of the input (to the right) as needed
    // basically all the characters except a's we can add 2 a's to each character and when there are consecutive a's, we count them and add 2-count in those cases
    private static int maxInserts(String input) {
        if (input == null || input.length() == 0) {
            return -1;
        }

        int result = 0;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == 'a') {
                count++;
            } else {
                result += 2 - count;
                count = 0;
            }
            if (count == 3) {
                return -1;
            }
        }
        if (input.charAt(input.length() - 1) != 'a') {
            result += 2;
        } else {
            result += 2 - count;
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(maxInserts("aabab"), 3);
        assertEquals(maxInserts("dog"), 8);
        assertEquals(maxInserts("aa"), 0);
        assertEquals(maxInserts("baaaa"), -1);
    }
}
