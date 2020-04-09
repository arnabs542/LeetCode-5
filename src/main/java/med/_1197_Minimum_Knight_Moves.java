package med;

import org.testng.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 4/8/20.
 * <p>
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 * </p>
 */
public class _1197_Minimum_Knight_Moves {
    // core logic: the meat of the problem lies in interpreting this is as a directed graph problem where each point in the grid is a node and edges from the node being all the possible knight moves from that node
    // we calculate the distance from every node to all its edges and whenever we encounter the target node, it means that's the shortest distance possible as we navigate through BFS
    // keep a track of visited edges so we don't keep them visiting infinitely
    // some more explanation here: https://leetcode.com/problems/minimum-knight-moves/discuss/387034/JAVA-BFS-Solution-with-explanation
    // TC: O(n)
    private static int minKnightMoves(int x, int y) {
        int[][] dirs = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
        Queue<int[]> queue = new LinkedList<>();
        HashMap<Integer, HashMap<Integer, Integer>> visited = new HashMap<>();
        HashMap<Integer, Integer> distMap = new HashMap<>();
        distMap.put(0, 0);  // dist from the curr node to itself is zero
        visited.put(0, distMap);
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] move = queue.poll();
            if (move[0] == x && move[1] == y) {    // we encountered the target node, so return its distance. as we are doing BFS, the first time we encounter this should be the shortest possible distance
                return visited.get(x).get(y);
            }
            for (int[] dir : dirs) {
                int newX = move[0] + dir[0];
                int newY = move[1] + dir[1];

                int dist = visited.get(move[0]).get(move[1]) + 1;  // dist from prev parent node + 1
                if (visited.containsKey(newX) && visited.get(newX).containsKey(newY)) {   // if the edge is already visited, continue the loop
                    continue;
                }
                if (!visited.containsKey(newX)) {
                    visited.put(newX, new HashMap<>());
                }
                visited.get(newX).put(newY, dist);   // insert the distance entry to the newer edge from the node
                queue.offer(new int[]{newX, newY});
            }
        }
        return 0;  // the fact that solution always exists, it never reaches here except when both x and y are zeroes
    }

    public static void main(String[] args) {
        Assert.assertEquals(minKnightMoves(2, 1), 1);
        Assert.assertEquals(minKnightMoves(5, 5), 4);
    }
}
