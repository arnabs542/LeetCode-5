package random_practice.microsoftassessment;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/15/19.
 * <p>
 * Min Moves to Obtain String Without 3 Identical Consecutive Letters
 * </p>
 */
public class Min_Moves_Avoid_3_Consecutive {
    // when count of the single character ('a' or 'b') reaches 3, reset both of them to zero
    // in all the other cases, reset the other character to zero for all the moves (as the other character will not be consecutive any more)
    private static int minMoves(String input) {
        if (input == null || input.length() < 3) {
            return 0;
        }

        int count = 0, aCount = 0, bCount = 0;
        for (char ch : input.toCharArray()) {
            if (ch == 'a') {
                aCount++;
                bCount = 0;
            }
            if (ch == 'b') {
                bCount++;
                aCount = 0;
            }
            if (aCount == 3 || bCount == 3) {
                count++;
                aCount = 0;
                bCount = 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assertEquals(minMoves("baaaaa"), 1);
        assertEquals(minMoves("baaabbaabbba"), 2);
        assertEquals(minMoves("baabab"), 0);
    }
}
