package med;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 5/25/19.
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * </p>
 */
public class _74_Search2DMatrix {

    // core logic is that just consider the input as a sorted array rather than a 2*2 matrix and then perform a usual binary search
    // TC: O(log N), SC: O(1)
    private static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int low = 0, high = (matrix.length * matrix[0].length) - 1;

        while (low <= high) {
            int mid = (low + high) / 2;   // or mid = (low + high) >> 1
            int row = getRow(matrix, mid);
            int column = getColumn(matrix, mid);

            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    // these helper methods are very important to get the coordinates of given row and column for the middle element
    private static int getRow(int[][] matrix, int mid) {
        return mid / matrix[0].length;
    }

    private static int getColumn(int[][] matrix, int mid) {
        return mid % matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        assertTrue(searchMatrix(input, 3));

        int[][] input1 = new int[][]{{-10}, {-7}, {-5}};
        assertTrue(searchMatrix(input1, -10));
    }
}
