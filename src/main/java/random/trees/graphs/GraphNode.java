package random.trees.graphs;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by udaythota on 3/24/19.
 * Simple Graph Node Representation using adjacency list approach
 */
public class GraphNode {

    GraphNode(int v) {
        this.data = v;
        this.neighbours = new LinkedList<>();
    }

    private boolean visited;
    private List<GraphNode> neighbours;
    private int data;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addNeighbour(GraphNode neighbourNode) {
        this.neighbours.add(neighbourNode);
    }

    public List<GraphNode> getNeighbours() {
        return this.neighbours;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNeighbours(List<GraphNode> neighbours) {
        this.neighbours = neighbours;
    }
}
