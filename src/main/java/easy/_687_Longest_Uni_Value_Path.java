package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/18/20.
 * <p>
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 * The length of path between two nodes is represented by the number of edges between them.
 * </p>
 */
public class _687_Longest_Uni_Value_Path {
    private static int maxPath = 0;

    // core logic: the meat of the problem lies in finding the max of left and right at each and every node, update max at that point and return the max of left or right to its parent
    // this is similar to LC: 124 - binary tree find max path sum
    // TC: O(n), SC: O(h) - where h is the height of the tree. means recursive stack space could be up to h layers deep
    private static int longestUnivaluePath(TreeNode root) {
        helper(root);
        return maxPath;
    }

    private static int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = helper(root.left);   // just the normal traversal of tree
        int rightMax = helper(root.right);  // just the normal traversal of tree

        if (root.left != null && root.val == root.left.val) {   // find the left max at a node
            leftMax += 1;
        } else {
            leftMax = 0;   // reset it to zero when root and its left child values are not equal
        }
        if (root.right != null && root.val == root.right.val) {   // find the right max at a node
            rightMax += 1;
        } else {
            rightMax = 0;
        }
        maxPath = Math.max(maxPath, leftMax + rightMax);    // update the max at the node (as on that node, left + root + right path could be the max path)
        return Math.max(leftMax, rightMax);  // but only return the max of left and right paths to parent as those are the only valid paths that can be extended from its parent
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5Dup1 = new TreeNode(5);
        TreeNode node5Dup2 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node1Dup = new TreeNode(1);
        root.left = node4;
        root.right = node5Dup1;
        node4.left = node1;
        node4.right = node1Dup;
        node5Dup1.right = node5Dup2;
        assertEquals(longestUnivaluePath(root), 2);
    }
}