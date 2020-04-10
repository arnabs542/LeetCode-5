package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/9/20.
 * <p>
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 * </p>
 */
public class _766_Topelitz_Matrix {
    // core logic: simple observation: iterate through matrix and at every element compare its next corresponding element. if at any point they are not equal, return false
    // end indexes for i and j should stop at matrix.length - 2 to make sure we don't go out of array boundaries
    private static boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // exact same as above approach, except that we here start i and j from 1 and compare the previous elements in the diagnol
    private static boolean isToeplitzMatrix2(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Assert.assertTrue(isToeplitzMatrix(new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}}));
        Assert.assertTrue(isToeplitzMatrix2(new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}}));
    }
}
