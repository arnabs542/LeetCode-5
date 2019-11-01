package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/31/19.
 * <p>
 * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
 * The binary search tree is guaranteed to have unique values.
 * </p>
 */
public class _938_Range_Sum_BST {
    // core logic: use the properties of the BST to navigate the tree accordingly
    private static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val >= L && root.val <= R) {
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        } else if (root.val > L && root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else {
            return rangeSumBST(root.right, L, R);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node15 = new TreeNode(15);
        TreeNode node3 = new TreeNode(3);
        TreeNode node7 = new TreeNode(7);
        TreeNode node18 = new TreeNode(18);
        root.left = node5;
        root.right = node15;
        node5.left = node3;
        node5.right = node7;
        node15.right = node18;
        assertEquals(rangeSumBST(root, 7, 15), 32);
    }
}