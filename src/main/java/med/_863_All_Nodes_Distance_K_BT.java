package med;

import org.testng.Assert;

import java.util.*;

/**
 * Created by udaythota on 4/14/20.
 * <p>
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 * </p>
 */
public class _863_All_Nodes_Distance_K_BT {
    private static HashMap<TreeNode, TreeNode> nodeParentMap;

    // simple DFS (to pre populate the node parent relationship) and BFS (to traverse K levels from target level)
    // the meat of the problem lies in finding a way to navigate to the node ancestors. for this we first build the parent node relationship (map), using which we can navigate from node to its parent (ancestor)
    // once we build the map, do a simple BFS starting from the target level both the ways (children and parent). whenever we reach the kth level, add all elements from the level to the result.
    // TC: O(N) - since both BFS and DFS only takes O(N)
    private static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();   // so we don't keep adding the same nodes again and again: this is needed as we are adding both children and parents at the same time and hence we need to make sure we are not adding duplicates
        nodeParentMap = new HashMap<>();
        buildNodeParentMap(root, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);   // NOTE: add target node to the queue since we need to count the levels from here
        visited.add(target);

        while (!queue.isEmpty() && K >= 0) {   // run till we reach the kth level of elements from target
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                if (K == 0) {
                    result.add(node.val);
                }
                if (node.left != null && visited.add(node.left)) {
                    queue.offer(node.left);
                }
                if (node.right != null && visited.add(node.right)) {
                    queue.offer(node.right);
                }
                TreeNode parent = nodeParentMap.get(node);
                if (parent != null && visited.add(parent)) {
                    queue.offer(parent);
                }
            }
            K--;
        }
        return result;
    }

    // helper function to build the node and parent relationship
    // TC: O(N)
    private static void buildNodeParentMap(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        nodeParentMap.put(node, parent);
        buildNodeParentMap(node.left, node);
        buildNodeParentMap(node.right, node);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node2.left = node7;
        node2.right = node4;
        node1.left = node0;
        node1.right = node8;
        Assert.assertEquals(distanceK(root, node5, 2), Arrays.asList(7, 4, 1));
    }
}
