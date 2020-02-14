package easy;

import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/14/20.
 * <p>
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
 * </p>
 */
public class _783_Min_Dist_BST_Nodes {
    // core logic: the fact that inorder traversal on a BST returns us sorted elements in the ascending order, just do the traversal as is and compare the prev and curr elements and update the min value when needed
    // the min value should only be between prev and curr nodes as BST inorder returns sorted order of elements
    // TC: O(n)
    private static int minDiffInBST(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        if (root == null) {
            return minDiff;
        }
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        TreeNode current = root;
        Integer prev = null;

        while (current != null || !nodeStoreStack.isEmpty()) {
            if (current != null) {
                nodeStoreStack.push(current);
                current = current.left;
            } else {
                current = nodeStoreStack.pop();
                if (prev != null) {
                    minDiff = Math.min(minDiff, current.val - prev);
                }
                prev = current.val;
                current = current.right;
            }
        }
        return minDiff;
    }

    // recursive approach: same as above logic
    private static int minDiff = Integer.MAX_VALUE;
    private static Integer prev = null;

    private static int minDiffInBST2(TreeNode root) {
        if (root.left != null) {
            minDiffInBST2(root.left);
        }
        if (prev != null) {
            minDiff = Math.min(minDiff, root.val - prev);
        }
        prev = root.val;
        if (root.right != null) {
            minDiffInBST2(root.right);
        }
        return minDiff;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node6 = new TreeNode(6);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        root.left = node2;
        root.right = node6;
        node2.left = node1;
        node2.right = node3;
        assertEquals(minDiffInBST(root), 1);
        assertEquals(minDiffInBST2(root), 1);
    }
}