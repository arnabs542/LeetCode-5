package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/16/19.
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 */
public class _371_Sum_Of_Two_Integers {
    // core logic: first find the carry (a & b) and the sum (a ^ b) of 2 binaries. then left shift (<<) the carry by one bit and continue the process. left shift as the carry from current position should be applied (or added) to the next position
    private static int getSum(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }

        while (b != 0) {   // till carry is not zero
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;   // left shift carry by one bit and assign it to b
        }
        return a;
    }

    // simple recursive approach (similar to iterative approach)
    private static int getSum2(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getSum(a ^ b, (a & b) << 1);
    }

    // though not part of this problem, it might be useful
    private static int getSubtract(int a, int b) {
        while (b != 0) {
            int borrow = (~a) & b;
            a = a ^ b;
            b = borrow << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(getSum(1, 2), 3);
        assertEquals(getSum(-1, 3), 2);

        // test method: 2
        assertEquals(getSum2(1, 2), 3);
        assertEquals(getSum2(-1, 3), 2);

        // test subtraction
        assertEquals(getSubtract(3, 4), -1);
        assertEquals(getSubtract(7, 3), 4);
    }
}