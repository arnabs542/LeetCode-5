package med;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 5/25/19.
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * </p>
 */
public class _240_Search2DMatrix_II {
    /*
    We start search the matrix from TOP RIGHT CORNER, initialize the current position to top right corner,
    if the target is greater than the value in current position, then the target can not be in entire row of current position because the row is sorted,
    if the target is less than the value in current position, then the target can not in the entire column because the column is sorted too.
    We can rule out one row or one column each time, so the TC is O(m+n).
    */
    private static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0;
        int column = matrix[0].length - 1;

        while (column >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][column]) {
                return true;
            } else if (target < matrix[row][column]) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    /*
    Similar approach as the above solution except that we start from the BOTTOM LEFT CORNER
    TC: O(m+n)
     */
    private static boolean searchMatrixAlternate(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length - 1;
        int column = 0;

        while (row >= 0 && column <= matrix[0].length - 1) {
            if (matrix[row][column] == target) {
                return true;
            } else if (target < matrix[row][column]) {
                row--;
            } else {
                column++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] input1 = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int[][] input2 = new int[][]{{}};
        int[][] input3 = new int[][]{{-5}};
        int[][] input4 = new int[][]{{-1}, {-1}};

        assertTrue(searchMatrix(input1, 5));
        assertFalse(searchMatrix(input1, 20));
        assertFalse(searchMatrix(input2, 1));
        assertFalse(searchMatrix(input3, -2));

        assertTrue(searchMatrixAlternate(input1, 5));
        assertFalse(searchMatrixAlternate(input1, 20));
        assertFalse(searchMatrixAlternate(input2, 1));
        assertFalse(searchMatrixAlternate(input3, -2));
        assertFalse(searchMatrixAlternate(input4, 0));
    }
}
