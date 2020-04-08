package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/7/20.
 * <p>
 * Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 * </p>
 */
public class _311_Sparse_Matrix_Multiplication {
    // core logic: brute force solution: for every row and column, calculate the desired value by multiplying the appropriate row and column
    private static int[][] multiply(int[][] A, int[][] B) {
        if (A == null || B == null) {
            return null;
        }
        int aRows = A.length;
        int aCols = A[0].length;
        int bCols = B[0].length;

        int[][] result = new int[aRows][bCols];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // almost same as above logic, except that we here skip calculating the appropriate columns in B when the values in row are zeroes
    // TC: should still be same as above solution I think ??
    // TODO: optimal solution, but revisit the logic again
    private static int[][] multiply2(int[][] A, int[][] B) {
        if (A == null || B == null) {
            return null;
        }
        int aRows = A.length;
        int aCols = A[0].length;
        int bRows = B.length;   // NOTE: this is redundant as aCols == bRows in all the cases, but writing here just for the sake of convenience and to make the for loops more readable
        int bCols = B[0].length;
        int[][] result = new int[aRows][bCols];

        for (int i = 0; i < aRows; i++) {
            for (int k = 0; k < bRows; k++) {  // equivalent to aCols
                if (A[i][k] != 0) {
                    for (int j = 0; j < bCols; j++) {
                        result[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 0, 0}, {-1, 0, 3}};
        int[][] B = new int[][]{{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};

        int[][] result = new int[][]{{7, 0, 0}, {-7, 0, 3}};
        Assert.assertEquals(multiply(A, B), result);
        Assert.assertEquals(multiply2(A, B), result);
    }
}
