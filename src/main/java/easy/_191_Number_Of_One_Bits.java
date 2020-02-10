package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/10/20.
 * <p>
 * Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
 * </p>
 */
public class _191_Number_Of_One_Bits {
    // core logic: start counting one's from the least significant digit. once you count the 1 in LSD, we need to flip it to zero (keeping all the digits as is).
    // the trick for flipping the LSD '1' bit to '0' bit is to AND the current number (n) with the previous number (n-1)
    // TC: O(1) -> depends on number of 1's in binary. for 32-bit integer, its O(1), SC-> O(1)
    // better understood when put on paper, but for little more explanation, look here: https://leetcode.com/problems/number-of-1-bits/discuss/240132/Java-Solutions
    private static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {  // stop when the number becomes 0 (when all the bits in the numbers are zeroes)
            count++;
            n = n & (n - 1);  // flip the least significant '1' bit to '0' (keeping rest of the digits as is)
        }
        return count;
    }

    public static void main(String[] args) {
        assertEquals(hammingWeight(5), 2);
        assertEquals(hammingWeight(8), 1);
        assertEquals(hammingWeight(7), 3);
    }
}