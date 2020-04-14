package hard;

import java.util.*;

/**
 * Created by udaythota on 4/13/20.
 * <p>
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
 * </p>
 */
public class _1192_Critical_Connections_Network {
    // core logic: Tarjan's algorithm: perform the DFS, maintain the time and low link times and decide if the node (or edges connected to that) is a critical connection based on the nodes discover time and the neighbors low link time
    // O(V+E) -> same as typical DFS: int the worst case we could visit all the edges of the graph
    private static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] visited = new boolean[n];   // to make sure we don't visit the nodes again
        int[] lowLinkValues = new int[n];   // to maintain the low link values for each node
        int time = 0;  // to keep track of cycles in dfs

        // build the graph
        for (List<Integer> list : connections) {
            int nodeFrom = list.get(0);
            int nodeTo = list.get(1);
            graph.putIfAbsent(nodeFrom, new ArrayList<>());
            graph.putIfAbsent(nodeTo, new ArrayList<>());
            graph.get(nodeFrom).add(nodeTo);
            graph.get(nodeTo).add(nodeFrom);
        }

        // iterate through all the unvisited vertices in the graph
        /*for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsHelper(graph, i, -1, visited, lowLinkValues, time, result);  // parent node = -1 or i is not mattering much, why??
            }
        }*/
        dfsHelper(graph, 0, -1, visited, lowLinkValues, time, result);  // parent node = -1 or i is not mattering much, why??  equivalent to above commented loop
        return result;
    }

    // dfs helper method
    private static void dfsHelper(HashMap<Integer, List<Integer>> graph, int currentNode, int parentNode, boolean[] visited, int[] lowLinkValues, int time, List<List<Integer>> result) {
        time++;  // increment the time for every new visit in the dfs
        visited[currentNode] = true;   // to avoid infinite loop
        lowLinkValues[currentNode] = time;
        int currNodeDiscoverTime = time;

        for (int neighbor : graph.get(currentNode)) {
            if (neighbor == parentNode) {
                continue;
            }
            if (!visited[neighbor]) {
                dfsHelper(graph, neighbor, currentNode, visited, lowLinkValues, time, result);

                // this is what happens during backtracking: found the edge that connects strongly connected components
                if (currNodeDiscoverTime < lowLinkValues[neighbor]) {
                    result.add(Arrays.asList(currentNode, neighbor));
                }
            }
            lowLinkValues[currentNode] = Math.min(lowLinkValues[currentNode], lowLinkValues[neighbor]);  // update the low link values
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> input = Arrays.asList((Arrays.asList(0, 1)), (Arrays.asList(1, 2)), (Arrays.asList(2, 0)), (Arrays.asList(1, 3)));
        System.out.println(criticalConnections(4, input));
    }
}
