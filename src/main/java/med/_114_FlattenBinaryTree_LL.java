package _5_Longest_Palindromic_Substring;

import java.util.Stack;

import static _5_Longest_Palindromic_Substring.BinaryTreeUtils.printLevelOrder;

/**
 * Created by udaythota on 6/1/19.
 * <p>
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * </p>
 */
public class _114_FlattenBinaryTree_LL {

    // Hint: If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
    // DFS: do a pre-order traversal. keep track of prev and current nodes and change the links appropriately
    private static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }

            // except for the root node, adjust the links accordingly
            if (prev != null) {
                prev.right = current;
                prev.left = null;  // don't forget: once the current node (prev left link) is set to prev right link, set the prev left link to null
            }
            prev = current;  // assign current to  prev
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        flatten(root);
        printLevelOrder(root);
    }
}
