package med;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/9/19.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * </p>
 */
public class _337_HouseRobber_III {
    // simple recursion
    // core logic: Recurrence relation: i.e., how to get rob(root) from rob(root.left), rob(root.right), ... etc.
    // From the point of view of the tree root, there are only two scenarios at the end: root is robbed or is not.
    // If it is, due to the constraint that "we cannot rob any two directly-linked houses", the next level of subtrees that are available would be the four "grandchild-subtrees" (root.left.left, root.left.right, root.right.left, root.right.right).
    // However if root is not robbed, the next level of available subtrees would just be the two "child-subtrees" (root.left, root.right).
    // We only need to choose the scenario which yields the larger amount of money.
    private static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }

        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(root.val + val, rob(root.left) + rob(root.right));
    }

    // recursion with memoization
    private static int rob1(TreeNode root) {
        return rob1(root, new HashMap<TreeNode, Integer>());
    }

    private static int rob1(TreeNode root, HashMap<TreeNode, Integer> memoMap) {
        if (root == null) {
            return 0;
        }

        if (memoMap.containsKey(root)) {
            return memoMap.get(root);
        }

        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }

        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }
        int temp = Math.max(root.val + val, rob(root.left) + rob(root.right));
        memoMap.put(root, temp);
        return temp;
    }

    public static void main(String[] args) {
        // test case: 1
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.right = node4;
        assertEquals(rob(root), 7);

        // test case: 2
        assertEquals(rob1(root), 7);
    }
}