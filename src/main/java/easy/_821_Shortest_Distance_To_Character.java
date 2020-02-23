package easy;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/21/19.
 * <p>
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
 * </p>
 */
public class _821_Shortest_Distance_To_Character {
    // TC: O(S.length * P.length) -> as we do a linear scan through the string twice, once to find the char positions / indexed and next to find the absolute distances
    // SC: O(n) -> as we are using the position list to track the positions of the character
    private static int[] shortestToChar(String S, char C) {
        if (S == null || S.length() == 0) {
            return null;
        }
        int[] result = new int[S.length()];

        List<Integer> position = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                position.add(i);
            }
        }

        for (int i = 0; i < S.length(); i++) {
            int min = S.length();   // max length possible
            for (int pos : position) {
                min = Math.min(min, Math.abs(pos - i));
            }
            result[i] = min;
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(shortestToChar("loveleetcode", 'e'), new int[]{3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0});
    }
}