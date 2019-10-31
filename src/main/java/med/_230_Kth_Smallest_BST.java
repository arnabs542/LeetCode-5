package med;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 5/29/19.
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 */
public class _230_Kth_Smallest_BST {
    // core logic: inorder traversal: kth smallest element in BST is the kth element in the in-order traversal
    // TC: O(n) as each node in the tree is visited exactly once
    private static int kthSmallest(TreeNode root, int k) {
        int kthSmallest = 0;
        if (root == null || k <= 0) {
            return kthSmallest;
        }

        TreeNode current = root;
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        while (current != null || !nodeStoreStack.isEmpty()) {
            if (current != null) {
                nodeStoreStack.push(current);
                current = current.left;
            } else {
                current = nodeStoreStack.pop();
                int currentValue = current.val;
                k--;
                if (k == 0) {
                    kthSmallest = currentValue;
                    break;   // when you encounter the kth smallest element, exit the loop and return the value
                }
                current = current.right;
            }
        }
        return kthSmallest;
    }

    // Recursive Inorder Traversal Approach
    // the key is instead of using variables to track the result and count, use the array to set / get elements at fixed position: recursion is messy with variables due to recursive function calls
    private static int kthSmallestRecursive(TreeNode root, int k) {
        List<Integer> count = new ArrayList<>();
        count.add(k);
        int[] ret = new int[1];
        recursiveHelper(root, count, ret);
        return ret[0];
    }

    private static void recursiveHelper(TreeNode root, List<Integer> count, int[] ret) {
        if (root != null) {
            recursiveHelper(root.left, count, ret);
            count.set(0, count.get(0) - 1);
            if (count.get(0) == 0) {
                ret[0] = root.val;
            }
            recursiveHelper(root.right, count, ret);
        }
    }

    // global variables that would be used inside recursion function calls: local variables are confusing and easy to mess up with
    private int count = 0;
    private int result = 0;

    // same as above recursive approach (Inorder Traversal), except that instead of arrays, use global variables to track result and count
    private int kthSmallestRecursiveAlternate(TreeNode root, int k) {
        recursiveHelperAlternate(root, k);
        return result;
    }

    private void recursiveHelperAlternate(TreeNode root, int k) {
        if (root != null) {
            recursiveHelperAlternate(root.left, k);
            count++;
            if (count == k) {
                result = root.val;
                return;
            }
            recursiveHelperAlternate(root.right, k);
        }
    }

    public static void main(String[] args) {

        // test case: 1
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        assertEquals(kthSmallest(root, 1), 1);
        assertEquals(kthSmallestRecursive(root, 1), 1);
        assertEquals(new _230_Kth_Smallest_BST().kthSmallestRecursiveAlternate(root, 1), 1);

        // test case: 2
        TreeNode root1 = new TreeNode(5);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(2);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(1);
        root1.left = node4;
        root1.right = node5;
        node4.left = node6;
        node4.right = node7;
        node6.left = node8;
        assertEquals(kthSmallest(root1, 3), 3);
        assertEquals(kthSmallestRecursive(root1, 3), 3);
        assertEquals(new _230_Kth_Smallest_BST().kthSmallestRecursiveAlternate(root1, 3), 3);
    }
}
