package easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 11/9/19.
 * <p>
 * Invert a binary tree.
 * </p>
 */
public class _226_Invert_Binary_Tree {
    // simple level order traversal / BFS and as you traverse keep swapping the nodes at every level
    private static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            TreeNode t1 = temp.left;
            temp.left = temp.right;
            temp.right = t1;

            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return root;
    }

    // recursive approach: first reverse the left and right and repeat the same procedure for all nodes of the tree
    // TC: O(n) - as each node gets visited only once. SC: O(h) - where h is the height of the tree. this is the function call space
    private static TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftTemp = root.left;
        root.left = root.right;
        root.right = leftTemp;
        invertTree2(root.left);
        invertTree2(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);
        root.left = node2;
        root.right = node7;
        node2.left = node1;
        node2.right = node3;
        node7.left = node6;
        node7.right = node9;

        TreeNode newRoot = invertTree(root);
        BinaryTreeUtils.printLevelOrder(newRoot);
    }
}