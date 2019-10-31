package easy;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 10/30/19.
 * <p>
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * </p>
 */
public class _112_Path_Sum {
    private static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node11 = new TreeNode(11);
        TreeNode node13 = new TreeNode(13);
        TreeNode node4b = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        root.left = node4;
        root.right = node8;
        node4.left = node11;
        node8.left = node13;
        node8.right = node4b;
        node11.left = node7;
        node11.right = node2;
        node4b.right = node1;
        assertTrue(hasPathSum(root, 22));
    }
}
