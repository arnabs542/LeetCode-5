package easy;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 4/6/20.
 * <p>
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 * </p>
 */
public class _572_SubTree_Another_Tree {
    // just do a pre order traversal and at every step, see if the 2nd tree can be a sub tree of the 1st tree
    // TC: O(m*n) - where m is the number of nodes in the first tree and n is the number of nodes in the 2nd tree
    // in the worst case, we could traverse all the first tree (m times) and each time we call same tree function (n times). so, TC is: O(m*n)
    private static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (isSameTree(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    // helper method to see if both trees are identical: LC: 100
    // TC: O(n) - where n is the nodes in the 2nd tree
    private static boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val && isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.left = node4;
        root.right = node5;
        node4.left = node1;
        node4.right = node2;

        TreeNode root2 = new TreeNode(4);
        TreeNode node1Dup = new TreeNode(1);
        TreeNode node2Dup = new TreeNode(2);
        root2.left = node1Dup;
        root2.right = node2Dup;
        assertTrue(isSubtree(root, root2));
    }
}
