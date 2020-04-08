package med;

import org.testng.Assert;

import java.util.*;

/**
 * Created by udaythota on 4/7/20.
 * <p>
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * </p>
 */
public class _199_Binary_Tree_Right_Side_View {
    // simple BFS solution: the FIRST non null node from RIGHT at every level should be added to the result
    // TC: O(n), TC: O(w) - where w is width / size of the each level
    private static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result.add(node.val);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return result;
    }

    // core logic: DFS: at every level make sure, we only need to add first non null element from the right most
    // the meat of the problem lies in coming up with idea of comparing the result size and level
    // TC: O(n), SC: O(h) - where h is the height of the binary tree
    private static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfsHelper(root, 1, result);
        return result;
    }

    private static void dfsHelper(TreeNode root, int level, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (result.size() < level) {  // this makes sure that we only add 1 non null element from right at each level
            result.add(root.val);
        }
        dfsHelper(root.right, level + 1, result);
        dfsHelper(root.left, level + 1, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.right = node5;
        node3.right = node4;
        Assert.assertEquals(rightSideView(root), Arrays.asList(1, 3, 4));
        Assert.assertEquals(rightSideView2(root), Arrays.asList(1, 3, 4));
    }
}
