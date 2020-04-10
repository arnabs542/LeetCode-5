package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/9/20.
 * <p>
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.
 * A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other (on this case they are rotated in a different direction, in other words 2 or 5 gets mirrored); 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
 * Now given a positive number N, how many numbers X from 1 to N are good?
 * </p>
 */
public class _788_Rotated_Digits {
    // core logic: iterate from 0 to N, find all the valid numbers and return the count
    // NOTE: some more optimisations can also be done. deal with it later
    private static int rotatedDigits(int N) {
        int count = 0;
        for (int i = 0; i <= N; i++) {
            if (isValid(i)) {
                count++;
            }
        }
        return count;
    }

    // any time we encounter 3 or 4 or 7 in the number, just return false as reversing the number cannot be an other valid digit and hence return false
    // when we encounter a number in [2, 5, 6, 9], set the boolean to true, as returning one of these digits gives us an other number altogether and hence this might be a possible candidate for the valid number (as it could form a diff number in the end)
    // when we encounter a number in [1, 0], don't do anything, as returning those number gives us the same number and we cannot say if its a valid number or not without seeing other integers
    private static boolean isValid(int num) {
        boolean validFound = false;
        while (num != 0) {
            int rem = num % 10;
            if (rem == 2 || rem == 5 || rem == 6 || rem == 9) {
                validFound = true;
            }
            if (rem == 3 || rem == 4 || rem == 7) {
                return false;
            }
            num = num / 10;
        }
        return validFound;
    }

    public static void main(String[] args) {
        Assert.assertEquals(rotatedDigits(10), 4);
    }
}
