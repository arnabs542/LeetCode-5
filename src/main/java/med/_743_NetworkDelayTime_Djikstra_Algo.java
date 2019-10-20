package med;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/16/19.
 * <p>
 * There are N network nodes, labelled 1 to N.
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 * </p>
 */
public class _743_NetworkDelayTime_Djikstra_Algo {

    // Shortest Path using Djikstra Algorithm (BFS traversal)
    // Dijkstra's algorithm is based on repeatedly making the candidate move that has the least distance travelled.
    // TC: O(N log N), as potentially every edge gets added to the priority queue
    private static int networkDelayTime(int[][] times, int N, int K) {

        int result = -1;
        if (times == null || times.length == 0) {
            return result;
        }

        // build a dependent graph (with key as source and value as all its connected vertices and their corresponding weights)
        // key: source-vertex. value: value[0] -> target-vertex, value[1] -> weight for the edge
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        HashMap<Integer, Integer> distMap = new HashMap<>();  // to maintain the min distance of all the vertices from the source vertex

        // use priority queue to process the next available shortest path nodes (custom priority queue based on the distance from the current vertex)
        // p[0] -> vertex, p[1] -> min distance from the K
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((edge1, edge2) -> edge1[1] - edge2[1]);
        priorityQueue.offer(new int[]{K, 0});

        while (!priorityQueue.isEmpty()) {
            int[] currentNode = priorityQueue.poll();
            int currentNodeVal = currentNode[0];
            int currentDist = currentNode[1];

            if (distMap.containsKey(currentNodeVal)) {
                continue;
            }
            distMap.put(currentNodeVal, currentDist);
            if (graph.containsKey(currentNodeVal)) {
                for (int[] neighbor : graph.get(currentNodeVal)) {
                    if (!distMap.containsKey(neighbor[0])) {
                        priorityQueue.offer(new int[]{neighbor[0], currentDist + neighbor[1]});
                    }
                }
            }
        }

        if (distMap.size() != N) {   // visited vertex count not equal to number of given nodes
            return result;
        }

        // get the node with max distance which is the max network delay time
        for (int dist : distMap.values()) {
            result = Math.max(result, dist);
        }
        return result;
    }

    public static void main(String[] args) {
        // test case: 1
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        assertEquals(networkDelayTime(times, 4, 2), 2);

        // test case: 2
        int[][] times1 = new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 4}};
        assertEquals(networkDelayTime(times1, 3, 1), 3);
    }
}
