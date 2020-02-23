package easy;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/12/19.
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 */
public class _977_Squares_Sorted_Array {
    // trivial: brute force solution. see the below alternate optimal solution
    // TC: O(NlogN) - due to sorting operation
    private static int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        int[] output = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            output[i] = A[i] * A[i];
        }
        Arrays.sort(output);
        return output;
    }

    // keep filling the output array from right to left
    // core logic: as the input array is sorted, the last element in the result array should either be coming from first or last element in the input array. accordingly increment or decrement the pointers in the input array and proceed further
    private static int[] sortedSquares2(int[] A) {
        int[] output = new int[A.length];
        int i = 0;
        int j = A.length - 1;

        for (int k = j; k >= 0; k--) {
            if (Math.abs(A[i]) > Math.abs(A[j])) {
                output[k] = A[i] * A[i];
                i++;
            } else {
                output[k] = A[j] * A[j];
                j--;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        assertEquals(sortedSquares(new int[]{-4, -1, 0, 3, 10}), new int[]{0, 1, 9, 16, 100});
        assertEquals(sortedSquares(new int[]{-7, -3, 2, 3, 11}), new int[]{4, 9, 9, 49, 121});

        assertEquals(sortedSquares2(new int[]{-4, -1, 0, 3, 10}), new int[]{0, 1, 9, 16, 100});
        assertEquals(sortedSquares2(new int[]{-7, -3, 2, 3, 11}), new int[]{4, 9, 9, 49, 121});
    }
}
