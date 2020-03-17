package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/19/20.
 * <p>
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * </P>
 */
public class _304_Range_Sum_Query_2D {

    private int[][] dp;

    // pre compute the sum values. the dp grid stores the sum of values in the row till that column. dp[i][j] represents the sum of values till dp[i][j-1] + matrix[i][j]
    // TC: O(m*n), SC: O(m*n)
    private _304_Range_Sum_Query_2D(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        dp = new int[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[i][j + 1] = dp[i][j] + matrix[i][j];    // NOTE: we can still do with dp[i][j] (by just handling the j==0 and other cases separately) instead of dp[i][j+1], but this is to avoid corner cases like having only element in the grid, etc
            }
        }
    }

    // trivial: brute force. TLE in LC. may not be an accepted solution in the interview. see below solution
    // TC: O(m*n)
    // TODO: this can also be done with O(1) with pre computation. deal with it later
    private static int sumRegion(int[][] matrix, int row1, int col1, int row2, int col2) {
        int sum = 0;

        for (int row = row1; row <= row2; row++) {
            for (int col = col1; col <= col2; col++) {
                sum += matrix[row][col];
            }
        }
        return sum;
    }

    // the fact that this method is called multiple times means that we cache the pre computed sum values and when this method is called, we just use those sums to calculate the final sums
    // TC: O(m) - where m is the difference between row2 and row1
    private int sumRegion2(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            sum += dp[row][col2 + 1] - dp[row][col1];   // calculate the sum of desired values in each row
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        _304_Range_Sum_Query_2D range_sum_query_2D = new _304_Range_Sum_Query_2D(matrix);
        assertEquals(range_sum_query_2D.sumRegion2(2, 1, 4, 3), 8);
        assertEquals(range_sum_query_2D.sumRegion2(1, 1, 2, 2), 11);
        assertEquals(range_sum_query_2D.sumRegion2(1, 2, 2, 4), 12);
    }
}