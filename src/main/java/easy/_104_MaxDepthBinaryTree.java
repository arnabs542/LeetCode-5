package easy;

import org.testng.Assert;

import java.util.LinkedList;
import java.util.Queue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/3/19.
 * <p>
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * </p>
 */
public class _104_MaxDepthBinaryTree {

    // standard recursive approach: DFS
    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // iterative approach: BFS
    // core logic: depth of the tree is the number of levels while performing the level order traversal of a tree
    private static int maxDepth2(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;     // increment the depth when you process all the elements from one level
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        assertEquals(maxDepth(root), 3);
        assertEquals(maxDepth2(root), 3);
    }
}
