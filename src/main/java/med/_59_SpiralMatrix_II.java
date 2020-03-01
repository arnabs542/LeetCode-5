package med;

import java.util.Arrays;

/**
 * Created by udaythota on 5/16/19.
 * <p>
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * Example: Input: 3
 * Output:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 * </p>
 */
public class _59_SpiralMatrix_II {
    // the logic is very similar to spiral matrix - I (the only difference here is: we insert the elements in to the array rather than reading the elements from the array)
    private static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        if (n <= 0) {
            return result;
        }

        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        int count = 1;

        while (count <= n * n) {
            // traverse right
            for (int i = colStart; i <= colEnd; i++) {
                result[rowStart][i] = count++;
            }
            rowStart++;

            // traverse down
            for (int i = rowStart; i <= rowEnd; i++) {
                result[i][colEnd] = count++;
            }
            colEnd--;

            // traverse left
            for (int i = colEnd; i >= colStart; i--) {
                result[rowEnd][i] = count++;
            }
            rowEnd--;

            // traverse up
            for (int i = rowEnd; i >= rowStart; i--) {
                result[i][colStart] = count++;
            }
            colStart++;
        }
        return result;
    }

    // the logic is very similar to spiral matrix - I (the only difference here is: we insert the elements in to the array rather than reading the elements from the array)
    private static int[][] generateMatrixAlternate(int n) {
        int[][] result = new int[n][n];
        if (n <= 0) {
            return result;
        }

        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        int direction = 0;
        int count = 1;

        // this condition becomes false only when all the elements from the matrix are processed
        while (rowStart <= rowEnd && colStart <= colEnd) {
            if (direction == 0) {
                for (int i = colStart; i <= colEnd; i++) {
                    result[rowStart][i] = count++;
                }
                rowStart++;
            } else if (direction == 1) {
                for (int i = rowStart; i <= rowEnd; i++) {
                    result[i][colEnd] = count++;
                }
                colEnd--;
            } else if (direction == 2) {
                for (int i = colEnd; i >= colStart; i--) {
                    result[rowEnd][i] = count++;
                }
                rowEnd--;
            } else {
                for (int i = rowEnd; i >= rowStart; i--) {
                    result[i][colStart] = count++;
                }
                colStart++;
            }
            direction = (direction + 1) % 4;
        }
        return result;
    }

    public static void main(String[] args) {

        int[][] result = generateMatrix(3);
        System.out.println(Arrays.deepToString(result));

        int[][] result1 = generateMatrixAlternate(3);
        System.out.println(Arrays.deepToString(result1));
    }
}
