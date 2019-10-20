package _5_Longest_Palindromic_Substring;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/9/19.
 * <p>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * </p>
 */
public class _221_MaximalSquares {

    // dp: iterative: tabulation approach:
    // core logic: construct a dp array (to track the size of min sub array till that point) and update the result whenever needed
    // dp[i][j]: if matrix[i][j] == 1, get the min of other 3 coordinates and add 1 to it. this is the size of the min sub array
    // see https://www.youtube.com/watch?v=_Lf1looyJMU if you still need more information
    private static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] dp = new int[rows][columns];

        // update the first row and column
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 0; j < columns; j++) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                max = 1;
            }
        }

        // start filling the dp from 2nd row and column
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] += Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;   // area = size * size
    }

    public static void main(String[] args) {
        char[][] input = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        assertEquals(maximalSquare(input), 4);
    }
}
