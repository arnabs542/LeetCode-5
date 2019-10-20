package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 6/24/19.
 * <p>
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * </p>
 */
public class _48_Rotate_Image {

    /*
    The whole idea is:
    Move layer by layer: i tracks the layer and j tracks the desired elements in ith layer
    In the first loop, only the outer side of matrix will be scanned. Inner loop will not.
    Four Points/Values on each side of the matrix are scanned at the same time.
    The loop will only loop along the upper side of the matrix.(Attention: the upper-right corner is one exception.)
    If the length of matrix is n, the loop will scanned from matrix[0][0] to matrix[0][n-1-1].
    If outer side has been scanner, i = i+1, then j = i.
    The maximum of j is hard to understand. But because we scanned the array symmetrically,
    If the minimum of j is i, the maximum value should also n-i - c, here c is a constant.
     */
    // TC: O(rows * columns) - as every cell is visited only once
    private static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int length = matrix.length - 1;
        for (int i = 0; i <= length / 2; i++) {   // as it is square matrix, by the time you process half of the layers, rest half are already ordered
            for (int j = i; j < length - i; j++) {   // for every element in j, get all the possible coordinates and swap them
                int coordinate1 = matrix[i][j];
                int coordinate2 = matrix[j][length - i];
                int coordinate3 = matrix[length - i][length - j];
                int coordinate4 = matrix[length - j][i];

                // swap to new positions
                matrix[j][length - i] = coordinate1;
                matrix[length - i][length - j] = coordinate2;
                matrix[length - j][i] = coordinate3;
                matrix[i][j] = coordinate4;
            }
        }
    }


    // core logic: transpose the matrix and flip it symmetrically / horizontally
    // though the time complexity is high (due to multiple iterations), this is simple and straight forward. better understood with an example
    private static void rotate2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        // transpose the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // flip it symmetrically
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    // simple utility to print the matrix (just for testing purposes)
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // test method: 1
        int[][] matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        System.out.println("Matrix before rotating: ");
        printMatrix(matrix);
        System.out.println("Matrix after rotating: ");
        rotate(matrix);
        printMatrix(matrix);

        // test method: 2
        int[][] matrix2 = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        System.out.println("Matrix2 before rotating: ");
        printMatrix(matrix2);
        System.out.println("Matrix2 after rotating: ");
        rotate2(matrix2);
        printMatrix(matrix2);
    }
}
