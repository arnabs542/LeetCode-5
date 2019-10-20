package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 5/18/19.
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * </p>
 */

// Most of the DP solutions can / should be approached using 3 approaches:
// Approach 1: Brute force / Simple Recursion (usually TC is O(2^n))
// Approach 2: Memoization / Top Down Approach (Recursive with caching): Start from bigger problems (n, n-1..) and approach towards smaller cases (2, 1, 0..)
// Approach 3: Tabulation / Bottom Up Approach (Iterative with caching): Start with smaller problems (0, 1, 2..) and approach towards larger cases (n-1, 1,..)
public class _62_UniquePaths {

    // Approach 1: BRUTE FORCE: simple recursion
    // TC: O(2^n) as each value in the grid is calculated AT LEAST twice
    private static int uniquePathsRecursion(int m, int n) {
        return uniquePathsRecursionHelper(m - 1, n - 1);
    }

    private static int uniquePathsRecursionHelper(int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        }
        if (m == 0 || n == 0) {
            return 1;
        }
        return uniquePathsRecursionHelper(m - 1, n) + uniquePathsRecursionHelper(m, n - 1);
    }

    /*
    Approach 2: DP: Tabulation / Bottom - Up approach : TC: O(m*n), SC: O(m*n)
    core logic is that, except the first row and first column (for which the number of paths is 1), the number of paths to get to any other block in grid is the sum of paths from its adjacent columns (one up and one left)
    For example - when n = 4 and m = 6, table will look like this:
    1	1	1	1	1	1
    1	2	3	4	5	6
    1	3	6	10	15	21
    1	4	10	20	35	56
 */
    private static int uniquePathsTabulation(int m, int n) {
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    // Approach 3: DP: Memoization / Top - Down approach : TC: O(m*n), SC: O(m*n)
    private static int uniquePathsMemoization(int m, int n) {
        int[][] cache = new int[m][n];
        return uniquePathsHelper(m - 1, n - 1, cache);
    }

    private static int uniquePathsHelper(int m, int n, int[][] cache) {
        if (m == 0 || n == 0)
            return 1;

        if (cache[m][n] > 0) {
            return cache[m][n];  // return from the cache when present (when the grid value is > 0)
        }
        cache[m][n] = uniquePathsHelper(m - 1, n, cache) + uniquePathsHelper(m, n - 1, cache);   // when not present in cache, calculate it and add to cache
        return cache[m][n];
    }

    // Approach 3.1:  same as the above memoized approach: slightly simpler version of that
    // core logic: populate / fill all the grid values (calculate the grid value ONLY ONCE)
    private static int uniquePathsMemoization2(int m, int n) {
        int[][] grid = new int[m][n];
        return uniquePathsMemoization2Helper(m - 1, n - 1, grid);
    }

    private static int uniquePathsMemoization2Helper(int m, int n, int[][] grid) {
        if (m == 0 || n == 0)
            return 1;

        if (grid[m][n] == 0) {   // calculate ONLY ONCE: when the grid value is > 0, it means the value is already calculated. so don't calculate again
            grid[m][n] = uniquePathsHelper(m - 1, n, grid) + uniquePathsHelper(m, n - 1, grid);
        }
        return grid[m][n];
    }

    public static void main(String[] args) {
        assertEquals(uniquePathsRecursion(3, 2), 3);
        assertEquals(uniquePathsRecursion(3, 4), 10);
        assertEquals(uniquePathsRecursion(7, 3), 28);

        assertEquals(uniquePathsMemoization(3, 2), 3);
        assertEquals(uniquePathsMemoization(3, 4), 10);
        assertEquals(uniquePathsMemoization(7, 3), 28);

        assertEquals(uniquePathsMemoization2(3, 2), 3);
        assertEquals(uniquePathsMemoization2(3, 4), 10);
        assertEquals(uniquePathsMemoization2(7, 3), 28);

        assertEquals(uniquePathsTabulation(3, 2), 3);
        assertEquals(uniquePathsTabulation(3, 4), 10);
        assertEquals(uniquePathsTabulation(7, 3), 28);
    }
}
