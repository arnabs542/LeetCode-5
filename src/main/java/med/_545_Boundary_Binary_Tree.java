package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 3/14/20.
 * <p>
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)
 * </p>
 */
public class _545_Boundary_Binary_Tree {
    // simple recursive function: first find the left boundary, find left side of the leaves, find right side of the leaves, and in the end find the right boundary
    // TC: O(n) - as each of the 3 helper methods have the same time complexity of O(n)
    private static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        leftBoundary(root.left, result);
        findLeaves(root.left, result);
        findLeaves(root.right, result);
        rightBoundary(root.right, result);
        return result;
    }

    // to find the left boundary: make sure to process the node in the beginning
    // TC: O(n)
    private static void leftBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null)) {   // either the node is null or it is a leaf node: so don't process it
            return;
        }
        result.add(root.val);  // NOTE: as we need the result in anti clock wise direction, we need to do something similar to pre order traversal (process the node in the beginning)
        if (root.left != null) {   // navigate to the left till it exists, if it doesn't exist navigate right
            leftBoundary(root.left, result);
        } else {
            leftBoundary(root.right, result);
        }
    }

    // to find the right boundary: make sure to process the node in the end
    // TC: O(n)
    private static void rightBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null)) {   // either the node is null or it is a leaf node: so don't process it
            return;
        }
        if (root.right != null) {   // navigate to the right till it exists, if it doesn't exist navigate left
            rightBoundary(root.right, result);
        } else {
            rightBoundary(root.left, result);
        }
        result.add(root.val);   // NOTE: as we need the result in anti clock wise direction, we need to do something similar to post order traversal (process the node in the end)
    }

    // to find the leaves (either left or the right boundary side)
    // TC: O(n)
    private static void findLeaves(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {   // leaf node: so process it
            result.add(root.val);
            return;
        }
        findLeaves(root.left, result);
        findLeaves(root.right, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.left = node7;
        node5.right = node8;
        node3.left = node6;
        node6.left = node9;
        node6.right = node10;
        assertEquals(boundaryOfBinaryTree(root), Arrays.asList(1, 2, 4, 7, 8, 9, 10, 6, 3));
    }
}