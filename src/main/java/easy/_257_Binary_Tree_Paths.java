package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udaythota on 3/15/20.
 * <p>
 * Given a binary tree, return all root-to-leaf paths.
 * </p>
 */
public class _257_Binary_Tree_Paths {
    // core logic: simple DFS: the meat of the problem lies in using the string builder in the right way (resetting the length when needed)
    // TC: O(n) - as we are visiting each node only once
    private static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        StringBuilder stringBuilder = new StringBuilder();
        binaryTreePathHelper(root, stringBuilder, result);
        return result;
    }

    // dfs helper: make sure to reset the string builder length so we don't create multiple string builders at the same time
    private static void binaryTreePathHelper(TreeNode root, StringBuilder stringBuilder, List<String> result) {
        if (root == null) {
            return;
        }

        int length = stringBuilder.length();
        stringBuilder.append(root.val);

        if (root.left == null && root.right == null) {   // when leaf node, add the string to result
            result.add(stringBuilder.toString());
        } else {
            stringBuilder.append("->");
            binaryTreePathHelper(root.left, stringBuilder, result);
            binaryTreePathHelper(root.right, stringBuilder, result);
        }
        stringBuilder.setLength(length);   // after every dfs, reset the string builder length (to what it was before dfs) so it can be reused for other strings
    }

    // method 2: similar to above idea, just a diff way of implementation
    // NOTE: though this looks simple, this creates multiple strings inside and hence this method is expensive (in terms of string manipulations) and has high time complexity. so use the 1st solution itself.
    private static List<String> binaryTreePaths2(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        binaryTreePathHelper2(root, "", result);
        return result;
    }

    // dfs helper: though this method looks simple, this creates new string for every dfs call (as strings are immutable) and hence making this method time intensive and less efficient
    private static void binaryTreePathHelper2(TreeNode root, String string, List<String> result) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {   // when leaf node, add the string to result
            result.add(string + root.val);
        }
        if (root.left != null) {
            binaryTreePathHelper2(root.left, string + root.val + "->", result);
        }
        if (root.right != null) {
            binaryTreePathHelper2(root.right, string + root.val + "->", result);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.right = node5;
        System.out.println(binaryTreePaths2(root));
    }
}