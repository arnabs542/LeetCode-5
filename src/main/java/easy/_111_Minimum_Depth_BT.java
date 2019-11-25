package easy;

import java.util.LinkedList;
import java.util.Queue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 11/19/19.
 * <p>
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * </p>
 */
public class _111_Minimum_Depth_BT {
    // NOTE: simple BFS: but see the below solution, which has similar approach to the max depth of the tree
    private static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> nodeStoreQueue = new LinkedList<>();
        // This queue always store 2 values (except for the root nodes), first value for count of left tree nodes and other for count of right tree nodes
        Queue<Integer> nodeValueCount = new LinkedList<>();
        nodeStoreQueue.offer(root);
        nodeValueCount.offer(1);

        while (!nodeStoreQueue.isEmpty()) {
            TreeNode temp = nodeStoreQueue.poll();
            Integer count = nodeValueCount.poll();
            if (temp.left == null && temp.right == null)
                return count;
            if (temp.left != null) {
                nodeStoreQueue.offer(temp.left);
                nodeValueCount.offer(count + 1);
            }
            if (temp.right != null) {
                nodeStoreQueue.offer(temp.right);
                nodeValueCount.offer(count + 1);
            }
        }
        return nodeValueCount.poll();
    }

    // alternative BFS solution. this is SIMILAR to max depth solution
    // the only difference is the extra condition to stop the process and return the level when both left and right trees for a node are null (we only need min depth!)
    private static int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node   .right == null) {  // encountered a child node. so this should be the min depth of the tree
                    return level;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return level;
    }

    // simple recursive DFS solution, but not a great approach for the trees with one higher depths on one side and lower depths on the other side
    // its better to use the BFS for the solutions which requires min distance / min depth / min ways possible
    private static int minDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return minDepth3(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth3(root.left) + 1;
        }
        return Math.min(minDepth3(root.left), minDepth3(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        root.left = node9;
        root.right = node20;
        node20.left = node15;
        node20.right = node7;
        assertEquals(minDepth(root), 2);
    }
}