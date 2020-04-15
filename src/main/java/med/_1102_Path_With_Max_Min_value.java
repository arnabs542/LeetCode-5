package med;

import org.testng.Assert;

import java.util.PriorityQueue;

/**
 * Created by udaythota on 4/14/20.
 * <p>
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
 * The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
 * A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
 * </p>
 */
public class _1102_Path_With_Max_Min_value {
    // core logic: BFS + priority queue. as we always need to go in the max path, we add all the neighbors of a cell to the pq (max heap sorted by grid value), so the next element in the pq will always be the greatest path element. keep an additional variable in the pq which keeps track of the min value processed on the current path. when we reached the end, return this min value
    // TC: O(VlogV + E) -> where V is the total number of elements in the matrix and E is the number of neighbors (constant). queue will not have more than V elements and queue operations take logV time
    private static int maximumMinimumPath(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);   // max heap: int[2] stores the min path value encountered till now
        queue.offer(new int[]{0, 0, A[0][0]});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();

            if (cell[0] == rows - 1 && cell[1] == cols - 1) {     // reached the end: return the min value
                return cell[2];
            }
            visited[cell[0]][cell[1]] = true;

            for (int[] dir : dirs) {   // explore all the 4 neighbors
                int neighborRow = dir[0] + cell[0];
                int neighborCol = dir[1] + cell[1];

                if (neighborRow < 0 || neighborCol < 0 || neighborRow >= rows || neighborCol >= cols || visited[neighborRow][neighborCol]) {
                    continue;
                }
                int minTillNow = Math.min(A[neighborRow][neighborCol], cell[2]);
                queue.offer(new int[]{neighborRow, neighborCol, minTillNow});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Assert.assertEquals(maximumMinimumPath(new int[][]{{5, 4, 5}, {1, 2, 6}, {7, 4, 6}}), 4);
        Assert.assertEquals(maximumMinimumPath(new int[][]{{3, 4, 6, 3, 4}, {0, 2, 1, 1, 7}, {8, 8, 3, 2, 7}, {3, 2, 4, 9, 8}, {4, 1, 2, 0, 0}, {4, 6, 5, 4, 3}}), 3);
    }
}
