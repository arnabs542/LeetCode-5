package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/27/20.
 * <p>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * </p>
 */
public class _200_Number_Of_Islands {
    // core logic: iterate through the grid and perform the dfs (horizontal and vertical from the current grid position: right, left, up and bottom) and add all the islands
    // TC: O(m*n) - where m is number of rows and n is number of columns
    // SC: O(m*n) - where m is number of rows and n is number of columns
    // TODO: can also be solved through BFS. pursue it later
    private static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    numIslands++;  // increment the count whenever you perform a dfs
                }
            }
        }
        return numIslands;  // number of islands is the cumulative sum of islands present in the grid
    }

    // helper method to perform the dfs on the grid
    // don't forget to mark each visited node as '0' when performing dfs
    private static void dfs(char[][] grid, int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
            grid[i][j] = '0';   // IMPORTANT: what this means is that, when we perform DFS, mark its adjacent lands (to '0' - as they all form the same island) and we don't count these islands multiple times
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }
    }

    public static void main(String[] args) {
        assertEquals(numIslands(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}), 1);
        assertEquals(numIslands(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}), 3);
    }
}