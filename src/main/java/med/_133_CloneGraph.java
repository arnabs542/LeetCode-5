package med;

import java.util.*;

/**
 * Created by udaythota on 6/16/19.
 * <p>
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * </p>
 */
public class _133_CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // core logic: perform a simple BFS - visit all the nodes of the original graph, get its neighbours and replicate the same structure to the new graph
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node newNode = new Node(node.val, new ArrayList<>());

        // to keep track of visited nodes map (key -> originalNode, value -> newNode)
        HashMap<Node, Node> visitedNodeMap = new HashMap<>();
        visitedNodeMap.put(node, newNode);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node parentNode = queue.poll();
            List<Node> neighbours = parentNode.neighbors;

            for (Node neighbour : neighbours) {
                if (!visitedNodeMap.containsKey(neighbour)) {
                    Node newNeighbor = new Node(neighbour.val, new ArrayList<>());
                    visitedNodeMap.put(neighbour, newNeighbor);   // if the map doesn't have an entry for the neighbour node, create an entry for it
                    queue.offer(neighbour);
                }
                visitedNodeMap.get(parentNode).neighbors.add(visitedNodeMap.get(neighbour));  // populate the neighbours for new node
            }
        }
        return newNode;
    }
}
