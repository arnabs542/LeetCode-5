package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/27/19.
 * <p>
 * Given a complete binary tree, count the number of nodes.
 * </p>
 */
public class _222_Count_Complete_Tree_Nodes {

    // TC: O(n) - this is a trivial solution, see below for the optimized solution
    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // TODO: think of a more intuitive solution
    // TC: 2 * O(logn)
    private static int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) + countNodes(root.right);
        } else {
            return (1 << rightHeight) + countNodes(root.left);
        }
    }

    private static int height(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        assertEquals(countNodes(root), 6);
    }
}