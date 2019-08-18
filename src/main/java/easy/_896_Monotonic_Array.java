package easy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


/**
 * Created by udaythota on 8/17/19.
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 */
public class _896_Monotonic_Array {
    // core logic: check for the first 2 unequal numbers to see find if the sequence is monotonically increasing or decreasing.
    // from there, check the remaining array to see if the sequence continues to have the same condition
    // see the below solutions for more optimal ones
    // TC: O(n)
    private static boolean isMonotonic(int[] A) {
        if (A.length <= 2) {
            return true;
        }

        int i = 0;
        while (i < A.length && A[i] == A[i + 1]) {   // to get to the first 2 unequal numbers
            i++;
            if (i == A.length - 1) {   // we reached end of the array (this means all the numbers are equal), so return true
                return true;
            }
        }

        boolean increasing = false;
        if (A[i] < A[i + 1]) {
            increasing = true;
        }

        for (int j = i; j < A.length - 1; j++) {
            if (increasing) {
                if (A[j] > A[j + 1]) {
                    return false;
                }
            } else {
                if (A[j] < A[j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // core logic: we don't need to find first 2 unequal numbers to check if the sequence is monotonically increasing or decreasing. rather we can just check the first and last numbers in the array and decide the type of sequence
    // TC: O(n)
    private static boolean isMonotonic2(int[] A) {
        if (A.length <= 2) {
            return true;
        }

        boolean increasing = false;
        if (A[0] < A[A.length - 1]) {   // compare first and last numbers
            increasing = true;
        }

        for (int i = 0; i < A.length - 1; i++) {
            if (increasing) {
                if (A[i] > A[i + 1]) {    // sequence break
                    return false;
                }
            } else {
                if (A[i] < A[i + 1]) {  // sequence break
                    return false;
                }
            }
        }
        return true;
    }

    // core logic: one pass (simple variant): we just want to remember if it is monotone increasing or monotone decreasing.
    // in the end, if the sequence BOTH increased and decreased, return false, else return true
    // TC: O(n)
    private static boolean isMonotonic3(int[] A) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                increasing = false;
            }

            if (A[i + 1] > A[i]) {
                decreasing = false;
            }
        }
        return increasing || decreasing;
    }

    public static void main(String[] args) {
        // test method: 1
        assertTrue(isMonotonic(new int[]{1, 2, 2, 3}));
        assertTrue(isMonotonic(new int[]{6, 5, 4, 4}));
        assertTrue(isMonotonic(new int[]{1, 2, 4, 5}));
        assertTrue(isMonotonic(new int[]{1, 1, 1}));
        assertTrue(isMonotonic(new int[]{1, 1, 2}));
        assertFalse(isMonotonic(new int[]{1, 3, 2}));

        // test method: 2
        assertTrue(isMonotonic2(new int[]{1, 2, 2, 3}));
        assertTrue(isMonotonic2(new int[]{6, 5, 4, 4}));
        assertTrue(isMonotonic2(new int[]{1, 2, 4, 5}));
        assertTrue(isMonotonic2(new int[]{1, 1, 1}));
        assertTrue(isMonotonic2(new int[]{1, 1, 2}));
        assertFalse(isMonotonic2(new int[]{1, 3, 2}));

        // test method: 3
        assertTrue(isMonotonic3(new int[]{1, 2, 2, 3}));
        assertTrue(isMonotonic3(new int[]{6, 5, 4, 4}));
        assertTrue(isMonotonic3(new int[]{1, 2, 4, 5}));
        assertTrue(isMonotonic3(new int[]{1, 1, 1}));
        assertTrue(isMonotonic3(new int[]{1, 1, 2}));
        assertFalse(isMonotonic3(new int[]{1, 3, 2}));
    }
}