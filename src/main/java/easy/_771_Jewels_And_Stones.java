package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/14/20.
 * <p>
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
 * </p>
 */
public class _771_Jewels_And_Stones {
    // core logic: remember the jewels in a char array and when iterating through string, check the count of jewels accordingly
    // TC: O(J.length +  S.length)
    private static int numJewelsInStones(String J, String S) {
        int[] charArray = new int[255];
        for (Character ch : J.toCharArray()) {
            charArray[ch]++;
        }

        int jewelCount = 0;
        for (Character ch : S.toCharArray()) {
            if (charArray[ch] > 0) {
                jewelCount++;
            }
        }
        return jewelCount;
    }

    // though its done in single loop, the indexOf function still requires at least O(n) time and hence overall its not linear time. so in terms of performance, this is not a great solution (when compared to first one), except that this doesn't use extra space
    // TC: O(SJ)
    private static int numJewelsInStones2(String J, String S) {
        int jewelCount = 0;
        for (Character ch : S.toCharArray()) {
            if (J.indexOf(ch) != -1) {   // indexOf returns -1 when there is no such character
                jewelCount++;
            }
        }
        return jewelCount;
    }

    public static void main(String[] args) {
        assertEquals(numJewelsInStones("aA", "aAAbbbb"), 3);
        assertEquals(numJewelsInStones("z", "ZZ"), 0);

        assertEquals(numJewelsInStones2("aA", "aAAbbbb"), 3);
        assertEquals(numJewelsInStones2("z", "ZZ"), 0);
    }
}