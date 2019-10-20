package _5_Longest_Palindromic_Substring;

import java.util.Stack;

/**
 * Created by udaythota on 8/14/19.
 * <p>
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
 * </p>
 */
public class _426_Convert_BST_DoubleLL {

    // core logic: do an inorder traversal and in the process, keep the prev pointer so you can update the pointer (left) from current to prev
    // after end of the iteration, make sure to attach the head and tail of the linked list to make it a circular linked list
    private static TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        TreeNode current = root;
        TreeNode prev = null;

        while (current != null || !nodeStoreStack.isEmpty()) {
            if (current != null) {
                nodeStoreStack.push(current);
                current = current.left;
            } else {
                current = nodeStoreStack.pop();
                if (prev != null) {  // update the left and right pointers accordingly
                    prev.right = current;
                    current.left = prev;
                } else {
                    root = current;   // save the root node so we can attach the first and last nodes later
                }
                prev = current;
                current = current.right;
            }
        }
        prev.right = root;   // to make the list circular: join the head and tail nodes
        root.left = prev;  // to make the list circular: join the head and tail nodes
        return root;
    }

    // inorder traversal: recursive approach
    private static TreeNode prev;

    private static TreeNode treeToDoublyListRecursive(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode dummy = new TreeNode(0);   // this is useful to join the head and tail nodes later
        prev = dummy;
        helper(root);
        prev.right = dummy.right;   // to make the list circular: join the head and tail nodes
        dummy.right.left = prev;    // to make the list circular: join the head and tail nodes
        return dummy.right;
    }

    private static void helper(TreeNode current) {
        if (current == null) {
            return;
        }
        helper(current.left);
        prev.right = current;
        current.left = prev;
        prev = current;
        helper(current.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        TreeNode newHead = treeToDoublyList(root);
        // BinaryTreeUtils.printLevelOrder(newHead);   // don't test printing as it is a circular linked list
    }
}