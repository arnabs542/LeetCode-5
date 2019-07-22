package med;

import random.trees.BinaryTreeNode;

import java.util.Stack;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by udaythota on 5/29/19.
 * <p>
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * </p>
 */
public class _98_ValidateBST {
    private static boolean isValidBST(TreeNode root) {
        return bstHelper(root, null, null);   // the reason we don't use INTEGER.MAX or INTEGER.MIN is to avoid corner cases when root is equal to that value
    }

    private static boolean bstHelper(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {   // to avoid corner cases with
            return false;
        }
        return bstHelper(root.left, min, root.val) && bstHelper(root.right, root.val, max);
    }

    // DON'T USE THIS: this passes for all the cases except for the corner cases like INTEGER.MAX or INTEGER.MIN. so use the above method
    public boolean bstHelper1(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return bstHelper(root.left, min, root.val) && bstHelper(root.right, root.val, max);
    }

    // Non Recursive Approach: Use the in order traversal: save the previous node and when the next node occurs, make sure its greater than the previous node's value. If not, return false
    // TC: O(n) as each node in the tree is visited exactly once
    private static boolean isValidBSTIterative(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        TreeNode current = root;
        Integer preVal = null;

        while (current != null || !nodeStoreStack.isEmpty()) {
            if (current != null) {
                nodeStoreStack.push(current);
                current = current.left;
            } else {
                current = nodeStoreStack.pop();
                int currentVal = current.val;

                if (preVal != null && currentVal <= preVal) {
                    return false;
                }
                preVal = currentVal;
                current = current.right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // test case: 1
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        root.left = node1;
        root.right = node3;
        assertTrue(isValidBST(root));
        assertTrue(isValidBSTIterative(root));

        // test case: 2
        TreeNode root1 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(6);
        root1.left = node4;
        root1.right = node5;
        node5.left = node6;
        node5.right = node7;
        assertFalse(isValidBST(root1));
        assertFalse(isValidBSTIterative(root1));
    }
}
