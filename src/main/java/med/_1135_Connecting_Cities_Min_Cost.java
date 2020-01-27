package med;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/26/20.
 * <p>
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.
 * </p>
 */
public class _1135_Connecting_Cities_Min_Cost {
    // core logic: this is a classic greedy algorithm to find the minimum spanning tree
    // use prim's algorithm to find the min spanning tree: use a priority queue to keep track of costs associated with all the connected nodes for each node in the graph (in the priority queue: min costs are processed first)
    // at each and every step, pop the edge with the least cost. if the edge doesn't exist in the MST (visited set), add its cost to the total min cost and add new edges starting from end node to queue
    // derived from this solution: https://leetcode.com/problems/connecting-cities-with-minimum-cost/discuss/357446/Simple-Java-solution-for-slow-learners-like-myself
    // TODO: min spanning tree can also be solved using kruskal's algorithm (union sort and find). investigate later
    private static int minimumCost(int n, int[][] connections) {
        if (n == 1) {
            return 0;
        }

        int minCost = 0;
        Set<Integer> visitedNodes = new HashSet<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);   // to sort by price associated with the connected nodes
        List<List<int[]>> graph = getGraph(n, connections);
        heap.offer(new int[]{1, 0});  // heap elements -> connected node, price

        while (!heap.isEmpty()) {
            int[] conn = heap.poll();
            int connNode = conn[0], price = conn[1];

            if (visitedNodes.contains(connNode)) {
                continue;
            }

            visitedNodes.add(connNode);
            minCost += price;
            for (int[] node : graph.get(connNode)) {
                if (!visitedNodes.contains(node[0])) {
                    heap.offer(new int[]{node[0], node[1]});
                }
            }
        }
        return visitedNodes.size() == n ? minCost : -1;
    }

    // helper method to generate the graph from connections
    private static List<List<int[]>> getGraph(int n, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] connection : connections) {
            int c1 = connection[0], c2 = connection[1], price = connection[2];
            graph.get(c1).add(new int[]{c2, price});
            graph.get(c2).add(new int[]{c1, price});
        }
        return graph;
    }

    public static void main(String[] args) {
        assertEquals(minimumCost(3, new int[][]{{1, 2, 5}, {1, 3, 6}, {2, 3, 1}}), 6);
        assertEquals(minimumCost(4, new int[][]{{1, 2, 3}, {3, 4, 4}}), -1);
    }
}