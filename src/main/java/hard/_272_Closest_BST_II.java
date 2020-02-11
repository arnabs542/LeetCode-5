package hard;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/11/20.
 * <p>
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * </p>
 */
public class _272_Closest_BST_II {
    private static List<Integer> closestKValues(TreeNode root, double target, int k) {

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
        assertEquals(closestKValues(root, 3.714286, 2), Arrays.asList(4, 3));
    }
}