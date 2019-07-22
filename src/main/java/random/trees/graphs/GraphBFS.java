package random.trees.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by udaythota on 3/24/19.
 * Simple BFS for a directed graph : More Object Oriented and simple to follow approach
 */
public class GraphBFS {

    private static void bfsDirectedGraph(GraphNode startNode) {
        Queue<GraphNode> nodeTraversalQueue = new LinkedList<>();
        nodeTraversalQueue.offer(startNode);
        startNode.setVisited(true);

        while (!nodeTraversalQueue.isEmpty()) {
            GraphNode currentNode = nodeTraversalQueue.poll();
            System.out.println(currentNode.getData());
            List<GraphNode> neighbourList = currentNode.getNeighbours();

            for (GraphNode neighbour : neighbourList) {
                if (neighbour != null && !neighbour.isVisited()) {
                    nodeTraversalQueue.offer(neighbour);
                    neighbour.setVisited(true);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphNode node40 = new GraphNode(40);
        GraphNode node10 = new GraphNode(10);
        GraphNode node20 = new GraphNode(20);
        GraphNode node30 = new GraphNode(30);
        GraphNode node60 = new GraphNode(60);
        GraphNode node50 = new GraphNode(50);
        GraphNode node70 = new GraphNode(70);

        node40.addNeighbour(node10);
        node40.addNeighbour(node20);
        node10.addNeighbour(node30);
        node20.addNeighbour(node10);
        node20.addNeighbour(node30);
        node20.addNeighbour(node60);
        node20.addNeighbour(node50);
        node30.addNeighbour(node60);
        node60.addNeighbour(node70);
        node50.addNeighbour(node70);

        System.out.println("The BFS traversal of the graph is: ");
        GraphBFS.bfsDirectedGraph(node40);
    }
}
