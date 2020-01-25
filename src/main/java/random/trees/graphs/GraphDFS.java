package random.trees.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by udaythota on 6/15/19.
 * Simple DFS for an directed graph : More Object Oriented and simple to follow approach
 */
public class GraphDFS {
    // core logic: iterative DFS: same as BFS except that replace the queue traversal with a stack
    private static void dfsDirectedGraph(GraphNode startNode) {
        Stack<GraphNode> nodeTraversalStack = new Stack<>();
        nodeTraversalStack.push(startNode);
        startNode.setVisited(true);

        while (!nodeTraversalStack.isEmpty()) {
            GraphNode currentNode = nodeTraversalStack.pop();
            System.out.println(currentNode.getData());
            List<GraphNode> neighbourList = currentNode.getNeighbours();

            for (GraphNode neighbour : neighbourList) {
                if (neighbour != null && !neighbour.isVisited()) {
                    nodeTraversalStack.push(neighbour);
                    neighbour.setVisited(true);
                }
            }
        }
    }

    // core logic: recursive DFS: doesn't use a stack, but rather uses recursion to do the traversal
    private static List<Integer> dfs2(GraphNode startNode, List<Integer> resultList) {
        if (startNode == null) {
            return resultList;
        }

        resultList.add(startNode.getData());
        startNode.setVisited(true);

        for (GraphNode neighbor : startNode.getNeighbours()) {
            if (!neighbor.isVisited()) {
                dfs2(neighbor, resultList);
            }
        }
        return resultList;
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

        /*System.out.println("The DFS traversal of the graph is: ");
        GraphDFS.dfsDirectedGraph(node40);*/

        System.out.println("The DFS 2 traversal of the graph is: ");
        List<Integer> result = GraphDFS.dfs2(node40, new ArrayList<>());
        System.out.println(result);
    }
}