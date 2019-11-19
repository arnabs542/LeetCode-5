package easy;

import java.util.LinkedList;
import java.util.Queue;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 7/3/19.
 * <p>
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * </p>
 */
public class _101_SymmetricTree {
    // recursive approach: process left and right trees separately
    private static boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }

    private static boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return (left.val == right.val) && helper(left.left, right.right) && helper(left.right, right.left);
    }

    // iterative approach (BFS): process left and right trees separately
    // when pushing elements to queue, first push the left value of left tree and right value of right tree and then the remaining 2 nodes so when you pop them, the first 2 elements are what we can directly compare
    private static boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) {   // when one node on left and the corresponding mirror node on right is null, its valid. so, further continue the process
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }

            // push to queue in the right order
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(3);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        assertTrue(isSymmetric(root));
        assertTrue(isSymmetric2(root));
    }
}