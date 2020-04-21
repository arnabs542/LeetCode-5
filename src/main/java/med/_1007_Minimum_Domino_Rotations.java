package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/20/20.
 * <p>
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * If it cannot be done, return -1.
 * </p>
 */
public class _1007_Minimum_Domino_Rotations {
    // core logic: keep the count of all the numbers in both the arrays and also the count of numbers present in the same indexes in both the arrays
    // if any number (from 1 to 7) is a valid candidate, the count of numbers at distinct positions - count of number at same indexes should be equal to length of the array (which means are able to fill the same candidate in all the positions)
    // amazing problem: better understood with visualization and intuition
    // TC: O(n), SC: O(n) (or O(1) as its always going to be of length 7)
    private static int minDominoRotations(int[] A, int[] B) {
        int length = A.length;
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] same = new int[7];

        for (int i = 0; i < A.length; i++) {
            countA[A[i]]++;
            countB[B[i]]++;
            if (A[i] == B[i]) {
                same[A[i]]++;
            }
        }
        for (int i = 1; i < 7; i++) {  // check for all the possible candidates (any number from 1 to 7 could be a possible candidate). when we encounter this candidate, return it
            if (countA[i] + countB[i] - same[i] == length) {
                return length - Math.max(countA[i], countB[i]);  // as we need the min number of moves, we need to chose an array where this number is already present in maximum number of indexes
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Assert.assertEquals(minDominoRotations(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}), 2);
    }
}
