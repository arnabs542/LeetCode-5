package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/12/20.
 * <p>
 * Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 * </p>
 */
public class _1026_Max_Diff_Node_Ancestor {

    private static int result;

    // core logic: top down approach: do a pre order traversal and in the process, calculate the max diff value (this could be the diff between current node value and min or current node value and max).
    // pass the min and max values of ancestors to the children nodes as we traverse down the tree which we can use to find the result values
    // TC: O(n) - as we visit each and every node only once
    private static int maxAncestorDiff(TreeNode root) {
        result = 0;
        maxDiffHelper(root, root.val, root.val);
        return result;
    }

    // dfs helper method
    private static void maxDiffHelper(TreeNode current, int min, int max) {
        if (current != null) {
            result = Math.max(result, Math.abs(current.val - min));   // the max value could be the diff between min value till now and current node val
            result = Math.max(result, Math.abs(current.val - max));   // the max value could be the diff between max value till now and current node val
            if (current.left != null) {
                maxDiffHelper(current.left, Math.min(min, current.left.val), Math.max(max, current.left.val));
            }
            if (current.right != null) {
                maxDiffHelper(current.right, Math.min(min, current.right.val), Math.max(max, current.right.val));
            }
        }
    }

    public static void main(String[] args) {
        // example 1
        TreeNode root = new TreeNode(8);
        TreeNode node3 = new TreeNode(3);
        TreeNode node10 = new TreeNode(10);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node14 = new TreeNode(14);
        TreeNode node13 = new TreeNode(13);

        root.left = node3;
        root.right = node10;
        node3.left = node1;
        node3.right = node6;
        node6.left = node4;
        node6.right = node7;
        node10.right = node14;
        node14.left = node13;
        Assert.assertEquals(maxAncestorDiff(root), 7);

        // example 2
        TreeNode root2 = new TreeNode(0);
        TreeNode node1Dup = new TreeNode(1);
        root2.right = node1Dup;
        Assert.assertEquals(maxAncestorDiff(root2), 1);
    }
}
