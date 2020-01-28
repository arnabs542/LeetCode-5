package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/27/20.
 * <p>
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 * </p>
 */
public class _366_Find_Leaves_Of_BinaryTree {
    // core logic: DFS solution: find the leaf nodes and then tell its parent to delete the leaves. the boolean value is to tell parent node to whether delete the child node or not
    private static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        while (root != null) {
            List<Integer> layer = new ArrayList<>();
            if (removeLeaves(root, layer)) {
                root = null;   // as both the children of this node are removed, now remove the parent node (make it null)
            }
            result.add(layer);
        }
        return result;
    }

    // helper method to remove the leaves: removes the left and right children of the node and returns true so the caller can now safely remove the parent node
    private static boolean removeLeaves(TreeNode root, List<Integer> layer) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            layer.add(root.val);
            return true;
        }

        if (removeLeaves(root.left, layer)) {
            root.left = null;
        }

        if (removeLeaves(root.right, layer)) {
            root.right = null;
        }
        return false;
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

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(4, 5, 3)));
        expected.add(new ArrayList<>(Arrays.asList(2)));
        expected.add(new ArrayList<>(Arrays.asList(1)));
        assertEquals(findLeaves(root), expected);
    }
}