package easy;

import java.util.LinkedList;
import java.util.Queue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/23/20.
 * <p>
 * In a given grid, each cell can have one of three values:
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 * </p>
 */
public class _994_Rotting_Oranges {
    // classic BFS: keep adding rotten oranges at each level and for every rotten orange, modify its adjacent fresh oranges to rotten and keep adding them to queue till there are no more rotten oranges
    // TC: O(n) - where N is the number of cells in the grid. SC: O(N) - for queue space
    private static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int columns = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});   // add rotten oranges to queue in the first level
                }
                if (grid[i][j] == 1) {
                    freshOranges++;    // count of fresh oranges. this is useful for us (in the end) to check if all the fresh oranges have rotten or not and return the value accordingly
                }
            }
        }

        int days = 0;   // this is basically number of levels in BFS / level order traversal
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // all the possible adjacent directions of a grid value
        while (!queue.isEmpty() && freshOranges > 0) {    // continue the BFS till there are no more valid fresh oranges that can rotten
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] node = queue.poll();
                for (int[] dir : directions) {
                    int x = node[0] + dir[0];
                    int y = node[1] + dir[1];

                    if (x < 0 || y < 0 || x >= rows || y >= columns || grid[x][y] == 0 || grid[x][y] == 2) {   // exceeded the boundary or the grid value is not a fresh orange, in that case just continue the loop
                        continue;
                    }
                    grid[x][y] = 2;    // mark the orange from fresh -> rotten
                    queue.offer(new int[]{x, y});  // add the rotten orange to queue
                    freshOranges--;
                }
            }
            days++;   // increment the level
        }
        return freshOranges == 0 ? days : -1;  // fact that there are no fresh oranges in the grid means all the fresh oranges in the grid have been rotten
    }

    public static void main(String[] args) {
        assertEquals(orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}), 4);
        assertEquals(orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}), -1);
    }
}