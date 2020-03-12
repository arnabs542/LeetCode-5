package med;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 3/11/20.
 * <p>
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * </p>
 */
public class _286_Walls_And_Gates {
    // classic DFS approach: perform DFS whenever you encounter an empty room and go deep from each empty room till you encounter a wall and cannot move
    // TC: O(m*n) -> as at each dfs, we are not traversing all the cells (because of rooms[i][j] < minDistance condition in the helper method) and hence we gradually decrease number of traversal steps at each dfs by half
    // this was asked to me at spotify onsite interview
    private static void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        int rowSize = rooms.length - 1;
        int colsSize = rooms[0].length - 1;
        for (int i = 0; i <= rowSize; i++) {
            for (int j = 0; j <= colsSize; j++) {
                if (rooms[i][j] == 0) {
                    dfsHelper(rooms, rowSize, colsSize, i, j, 0);    // perform DFS only when you encounter an empty room
                }
            }
        }
    }

    // dfs helper method
    private static void dfsHelper(int[][] rooms, int rowSize, int colSize, int i, int j, int minDistance) {
        if (i < 0 || i > rowSize || j < 0 || j > colSize || rooms[i][j] < minDistance) {
            return;
        }
        rooms[i][j] = minDistance;    // IMPORTANT: this is to mark the visited node, so as not to visit the same node again and there by not to go to an infinite recursion
        dfsHelper(rooms, rowSize, colSize, i + 1, j, minDistance + 1);
        dfsHelper(rooms, rowSize, colSize, i - 1, j, minDistance + 1);
        dfsHelper(rooms, rowSize, colSize, i, j + 1, minDistance + 1);
        dfsHelper(rooms, rowSize, colSize, i, j - 1, minDistance + 1);
    }

    // classic BFS: first add all the empty rooms to the queue, and as you process their valid neighbors, add those to the queue back and keep continuing till the queue is empty
    // TC: O(mn)
    private static void wallsAndGates2(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        int rowSize = rooms.length - 1;
        int colsSize = rooms[0].length - 1;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i <= rowSize; i++) {
            for (int j = 0; j <= colsSize; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] gate = queue.poll();
            for (int[] dir : dirs) {
                int newX = dir[0] + gate[0];
                int newY = dir[1] + gate[1];

                if (newX < 0 || newX > rowSize || newY < 0 || newY > colsSize || rooms[newX][newY] != Integer.MAX_VALUE) {
                    continue;
                }
                rooms[newX][newY] = rooms[gate[0]][gate[1]] + 1;
                queue.offer(new int[]{newX, newY});
            }
        }
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE}, {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1}, {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1}, {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}};
        wallsAndGates2(input);
        System.out.println(Arrays.deepToString(input));
    }
}