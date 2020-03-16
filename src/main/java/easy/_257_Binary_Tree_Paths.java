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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.right = node5;
        System.out.println(binaryTreePaths(root));
    }
}