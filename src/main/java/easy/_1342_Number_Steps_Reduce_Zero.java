package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/15/20.
 * <p>
 * Given a non-negative integer num, return the number of steps to reduce it to zero. If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
 * </p>
 */
public class _1342_Number_Steps_Reduce_Zero {
    // simple math, nothing fancy
    // TC: O(logN). as for every even number, we half it and though for the odd numbers, we reduce num by 1, in the next step it becomes even and hence in total it will still become a log operation
    private static int numberOfSteps(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num -= 1;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Assert.assertEquals(numberOfSteps(14), 6);
        Assert.assertEquals(numberOfSteps(8), 4);
        Assert.assertEquals(numberOfSteps(123), 12);
    }
}
