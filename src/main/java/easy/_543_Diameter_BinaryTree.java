package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/16/19.
 * <p>
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * </p>
 */
public class _543_Diameter_BinaryTree {
    private static int max = 0;

    // core logic: length of longest path at any node is the sum of left and right depths of that node
    // TC: O(n), SC: O(n)
    private static int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    private static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ldepth = depth(root.left);
        int rdepth = depth(root.right);
        max = Math.max(max, ldepth + rdepth);   // update the max as needed: depth can be sum of left and right depths and that itself can be the max possible depth
        return 1 + Math.max(ldepth, rdepth);   // returns the depth of the current node: return the valid path that its parent can use to find the depth
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        assertEquals(diameterOfBinaryTree(root), 3);
    }
}