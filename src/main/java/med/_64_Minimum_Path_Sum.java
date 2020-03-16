package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 5/19/19.
 * <p>
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * </p>
 */
public class _64_Minimum_Path_Sum {
    // Brute Force: Simple recursive Min_Hours_Challenge
    // TC: O(2^n) TODO: how come this will be exponential when we are already using a cache (the whole point of using cache is to not recalculate the grid elements again). check again later??
    // see the below tabulation / bottom up / iterative approach: much simpler
    private static int minPathSumRecursive(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] cache = new int[rows][columns];
        return minPathRecursiveHelper(grid, 0, 0, cache);
    }

    private static int minPathRecursiveHelper(int[][] grid, int rows, int columns, int[][] cache) {
        if (rows == grid.length || columns == grid[0].length) {
            return Integer.MAX_VALUE;
        }

        if (rows == grid.length - 1 && columns == grid[0].length - 1) {
            return grid[rows][columns];
        }

        if (cache[rows][columns] > 0) {
            return cache[rows][columns];
        }
        cache[rows][columns] = Math.min(minPathRecursiveHelper(grid, rows + 1, columns, cache), minPathRecursiveHelper(grid, rows, columns + 1, cache)) + grid[rows][columns];
        return cache[rows][columns];
    }

    // DP: Tabulation: Bottom up approach
    // TC: O(rows*columns), SC: O(1)
    private static int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int columns = grid[0].length;

        // fill the first column (sum of number in the current cell + sum of number in the previous column)
        for (int i = 1; i < rows; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // fill the first row (sum of number in the current cell + sum of number in the previous row)
        for (int j = 1; j < columns; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                grid[i][j] = Math.min(grid[i][j] + grid[i - 1][j], grid[i][j] + grid[i][j - 1]);   // calculate the minimum for every cell (minimum of the 2 paths) and update the grid
            }
        }
        return grid[rows - 1][columns - 1];
    }

    // DP: Memoization / Top Down Approach
    // TC: O(rows*columns), SC: O(rows*columns)
    private static int minPathSumMemoization(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] cache = new int[rows][columns];
        return minPathSumMemoizationHelper(grid, rows - 1, columns - 1, cache);
    }

    private static int minPathSumMemoizationHelper(int[][] grid, int rows, int columns, int[][] cache) {
        // Base Case 1
        if (rows < 0 || columns < 0) {
            return Integer.MAX_VALUE;  // this is because we want the min sum and when we do the min of Integer.Max and the grid value, it would always return the grid value
        }

        // Base Case 2: Return the grid value for grid[0][0]
        if (rows == 0 && columns == 0) {
            return grid[0][0];
        }

        // use the value from cache when available, else calculate and update the cache
        if (cache[rows][columns] > 0) {
            return cache[rows][columns];
        }
        cache[rows][columns] = Math.min(minPathSumMemoizationHelper(grid, rows - 1, columns, cache), minPathSumMemoizationHelper(grid, rows, columns - 1, cache)) + grid[rows][columns];
        return cache[rows][columns];
    }


    public static void main(String[] args) {
        assertEquals(minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}), 7);
        assertEquals(minPathSumRecursive(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}), 7);
        assertEquals(minPathSumMemoization(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}), 7);
    }
}