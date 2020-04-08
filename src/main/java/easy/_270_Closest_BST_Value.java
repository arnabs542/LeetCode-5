package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/7/20.
 * <p>
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * </p>
 */
public class _270_Closest_BST_Value {
    private static int result;

    // TC: O(h) - where h is height of the binary tree as we eliminate half of the tree at every point as we navigate down further
    private static int closestValue(TreeNode root, double target) {
        result = root.val;
        dfsHelper(root, target);
        return result;
    }

    // simple dfs helper method
    // we first calculate the closest value at the root, and if the target value is greater than root, we navigate to the right (as left side elements cannot have the closest value anyways), else we navigate to the left
    private static void dfsHelper(TreeNode root, double target) {
        if (root == null) {
            return;
        }
        if (Math.abs(root.val - target) < Math.abs(result - target)) {  // when the current absolute is lesser than the best absolute till now, update the best absolute value
            result = root.val;
        }
        if (target > root.val) {    // as the absolute best at root is already calculated, if the target is greater than root, the possible best abs can only be on the right side of the tree, else on the left side of the tree
            dfsHelper(root.right, target);
        } else {
            dfsHelper(root.left, target);
        }
    }

    // iterative approach: similar to above approach
    // TC: O(h) - where h is height of the binary tree
    private static int closestValue2(TreeNode root, double target) {
        int result = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - result)) {
                result = root.val;
            }
            if (target > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);

        root.left = node2;
        root.right = node5;
        node2.left = node1;
        node2.right = node3;
        Assert.assertEquals(closestValue(root, 3.714286), 4);
        Assert.assertEquals(closestValue2(root, 3.714286), 4);
    }
}
