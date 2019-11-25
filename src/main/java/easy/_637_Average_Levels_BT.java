package easy;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 11/24/19.
 * <p>
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * </p>
 */
public class _637_Average_Levels_BT {
    // core logic: simple BFS. keep the level sum variable as long instead of int to handle the int overflow case
    private static List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            long levelSum = 0;   // to make sure there is no integer overflow when the sum of both left and right vals exceed max int
            for (int i = 0; i < queueSize; i++) {
                TreeNode temp = queue.poll();
                levelSum += temp.val;

                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            result.add((double) levelSum / queueSize);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        root.left = node9;
        root.right = node20;
        node20.left = node15;
        node20.right = node7;

        assertEquals(averageOfLevels(root), Arrays.asList(3.0, 14.5, 11.0));
    }
}