package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/30/19.
 * <p>
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * </p>
 */
public class _235_LCA_BST {
    // simple recursive solution
    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {   // both p and q are less than root value, so lca should be on the left sub tree
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {  // both p and q are greater than root value, so lca should be on the right sub tree
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;   // p and q are on different sides, so root should be the LCA
        }
    }

    // simple iterative solution
    private static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else
                return root;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);
        TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node8;
        node2.left = node0;
        node2.right = node4;
        node8.left = node7;
        node8.right = node9;
        node4.left = node3;
        node4.right = node5;

        TreeNode lca = lowestCommonAncestor2(root, node2, node8);
        assertEquals(lca.val, 6);
    }
}
