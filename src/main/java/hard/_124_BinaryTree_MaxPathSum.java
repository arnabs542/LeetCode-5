package hard;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/29/19.
 * <p>
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 * </p>
 */
public class _124_BinaryTree_MaxPathSum {

    private static int finalMax = Integer.MIN_VALUE;

    // see the below alternate solution for the detailed explanation
    private static int maxPathSum(TreeNode root) {
        helper(root);
        return finalMax;
    }

    private static int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);
        finalMax = Math.max(finalMax, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    private static int maxPathSum2(TreeNode root) {
        helper2(root);
        return finalMax;
    }

    // though a bit lengthy, simple and straight forward to understand
    // max sum represents the maximum sum till the current node that can be used further (in the next recursive call)
    private static int helper2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = helper2(root.left);
        int rightSum = helper2(root.right);

        // compare leftSum + root val or rightSum + root val to select which value to send further
        int maxTillNow = Math.max(leftSum + root.val, rightSum + root.val);

        // if the root value is greater than any of the path
        maxTillNow = Math.max(maxTillNow, root.val);

        // if root + leftSum + rightSum is greater than any of the max till now, update the final max (though we are updating the final max, we shouldn't be propagating this further as only one of left or right path is the valid selection)
        int tempMax = Math.max(maxTillNow, root.val + leftSum + rightSum);
        finalMax = Math.max(finalMax, tempMax);  // update the final max if needed

        // return the max sum till the current node that can be processed further
        return maxTillNow;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(maxPathSum(root), 6);  // test method: 1
        assertEquals(maxPathSum2(root), 6);  // test method: 2

        TreeNode root1 = new TreeNode(-10);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root1.left = node1;
        root1.right = node2;
        node2.left = node3;
        node2.right = node4;
        assertEquals(maxPathSum(root1), 42);  // test method: 1
        assertEquals(maxPathSum2(root1), 42);  // test method: 2
    }
}
