package easy;

import java.util.LinkedList;
import java.util.Queue;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 11/18/19.
 * <p>
 * Given two binary trees, write a function to check if they are the same or not.
 * </p>
 */
public class _100_SameTree {
    // simple recursive solution
    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // simple iterative approach: using queue / level order traversal
    private static boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode firstNode = queue.poll();
            TreeNode secondNode = queue.poll();
            if (firstNode == null && secondNode == null) {   // when both nodes are null, nothing to check, just continue
                continue;
            } else if (firstNode == null || secondNode == null || firstNode.val != secondNode.val) {
                return false;
            }

            queue.offer(firstNode.left);
            queue.offer(secondNode.left);
            queue.offer(firstNode.right);
            queue.offer(secondNode.right);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root1.left = node2;
        root1.right = node3;

        TreeNode root2 = new TreeNode(1);
        TreeNode node2Dup = new TreeNode(2);
        TreeNode node3Dup = new TreeNode(3);
        root2.left = node2Dup;
        root2.right = node3Dup;
        assertTrue(isSameTree2(root1, root2));
    }
}