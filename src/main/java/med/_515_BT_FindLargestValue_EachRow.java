package _5_Longest_Palindromic_Substring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by udaythota on 6/4/19.
 * You need to find the largest value in each row of a binary tree.
 */
public class _515_BT_FindLargestValue_EachRow {

    // core logic: Simple BFS / Level Order Traversal. calculate max element at each level and add them to the result array
    // TC: O(n) - as each element in the tree is visited exactly once
    private List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        int currentRowMax;
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            currentRowMax = Integer.MIN_VALUE;
            for (int i = 0; i < queueSize; i++) {
                TreeNode current = queue.poll();
                currentRowMax = Math.max(currentRowMax, current.val);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            result.add(currentRowMax);
        }
        return result;
    }

    private List<Integer> result = new ArrayList<>();

    // core logic: recursive DFS approach
    private List<Integer> largestValues2(TreeNode root) {
        helperDfs(root, 1);
        return result;
    }

    private void helperDfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        if (result.size() < depth) {
            result.add(root.val);
        } else {
            result.set(depth - 1, Math.max(root.val, result.get(depth - 1)));
        }
        helperDfs(root.left, depth + 1);
        helperDfs(root.right, depth + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(9);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;

        // test method 1
        List<Integer> result = new _515_BT_FindLargestValue_EachRow().largestValues(root);
        System.out.println(result);

        // test method 2
        List<Integer> result2 = new _515_BT_FindLargestValue_EachRow().largestValues2(root);
        System.out.println(result2);
    }
}
