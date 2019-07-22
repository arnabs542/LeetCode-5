package med;

import java.util.Arrays;

/**
 * Created by udaythota on 5/19/19.
 * <p>
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 * </p>
 */
public class _73_SetMatrixZeroes {

    // the core logic is that, in the first pass iterating the matrix : you use the first row and first column to indicate / mark if the row / column needs to be zero
    // in the second pass, iterate through the matrix again (but leave first row and column as is) and set the matrix elements to zero for all the indicated rows / columns
    // IMPORTANT: as we are using the first row and column as indicators to remember zeroes for the future, we don't know if the row or column actually contained any zeroes in the first place. so process first row and column later
    private static int[][] setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean firstRow = false, firstColumn = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        firstRow = true;   // first row contained zero in the input matrix
                    }
                    if (j == 0) {
                        firstColumn = true;  // // first column contained zero in the input matrix
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // process first row and column separately when they contain zeroes in the input
        if (firstRow) {
            for (int i = 0; i < columns; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstColumn) {
            for (int j = 0; j < rows; j++) {
                matrix[j][0] = 0;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] result = setZeroes(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
        System.out.println(Arrays.deepToString(result));

        int[][] result1 = setZeroes(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
        System.out.println(Arrays.deepToString(result1));
    }
}
