package med;

import java.util.HashSet;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 3/14/20.
 * <p>
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 * </p>
 */
public class _694_Number_of_Distinct_Islands {
    // similar approach as LC 200: distinct number of islands except that we here also keep the track of entire island traversal in order to count the number of distinct islands. we use the 4 directions to keep the track of traversal paths
    // TC: O(m * n) - where m is number of rows and n is number of columns in the grid
    private static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        StringBuilder stringBuilder;
        HashSet<String> distinctIslands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    stringBuilder = new StringBuilder();
                    dfs(grid, i, j, stringBuilder, "o");   // start at origin
                    distinctIslands.add(stringBuilder.toString());
                }
            }
        }
        return distinctIslands.size();
    }

    // helper method to perform the dfs on the grid
    // we use 4 directions to keep track of visited paths. "b" indicates the backtracking path
    // don't forget to mark each visited node as '0' when performing dfs
    private static void dfs(int[][] grid, int i, int j, StringBuilder stringBuilder, String direction) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            stringBuilder.append(direction);
            grid[i][j] = 0;   // IMPORTANT: what this means is that, when we perform DFS, mark its adjacent lands (to '0' - as they all form the same island) so we don't count these islands multiple times
            dfs(grid, i + 1, j, stringBuilder, "d");  // navigated down
            dfs(grid, i - 1, j, stringBuilder, "u"); // navigated up
            dfs(grid, i, j + 1, stringBuilder, "r"); // navigated right
            dfs(grid, i, j - 1, stringBuilder, "l"); // navigated left
            stringBuilder.append("b");  // dfs completed: end of traversal. back to origin. mark as end. NOTE: this is needed as we need to track the ENTIRE PATH starting from origin and again back to origin. if this is commented the 3rd test case doesn't pass
        }
    }

    // same as above helper method. just an other way of rewriting the first if condition
    private static void dfs2(int[][] grid, int i, int j, StringBuilder stringBuilder, String direction) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        stringBuilder.append(direction);  // mark the current direction
        grid[i][j] = 0;   // IMPORTANT: what this means is that, when we perform DFS, mark its adjacent lands (to '0' - as they all form the same island) so we don't count these islands multiple times
        dfs2(grid, i + 1, j, stringBuilder, "d");
        dfs2(grid, i - 1, j, stringBuilder, "u");
        dfs2(grid, i, j + 1, stringBuilder, "r");
        dfs2(grid, i, j - 1, stringBuilder, "l");
        stringBuilder.append("b");
    }

    public static void main(String[] args) {
        assertEquals(numDistinctIslands(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}), 1);
        assertEquals(numDistinctIslands(new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}}), 3);
        assertEquals(numDistinctIslands(new int[][]{{1, 1, 0}, {0, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 1, 0}}), 2);
    }
}