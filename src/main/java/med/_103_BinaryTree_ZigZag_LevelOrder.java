package _5_Longest_Palindromic_Substring;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/1/19.
 * <p>
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * </p>
 */
public class _103_BinaryTree_ZigZag_LevelOrder {

    // simple BFS: use an additional variable to track the level. for alternate levels: reverse the order of elements in the temp list before adding them to the result
    // TC: O(n) as every node in the tree is visited exactly once
    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode temp = queue.poll();
                tempList.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            if (level % 2 == 1) {   // for alternate / odd levels: reverse the order of elements in the temp list
                Collections.reverse(tempList);
            }
            result.add(tempList);
            level++;
        }
        return result;
    }

    private static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode temp = queue.poll();
                // equivalent logic to the above reverse elements solution: for odd levels, keep adding elements to the first index of the temp list (which reverses the order of elements)
                if (level % 2 == 1) {
                    tempList.add(0, temp.val);
                } else {
                    tempList.add(temp.val);
                }

                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            result.add(tempList);
            level++;
        }
        return result;
    }

    public static void main(String[] args) {
        // test case: 1
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        List<List<Integer>> result = zigzagLevelOrder(root);
        List<List<Integer>> result1 = zigzagLevelOrder2(root);
        System.out.println(result);
        System.out.println(result1);
        assertEquals(result, result1);

        // test case: 2
        TreeNode root1 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(5);
        root1.left = node5;
        root1.right = node6;
        node5.left = node7;
        node6.right = node8;

        List<List<Integer>> result3 = zigzagLevelOrder(root1);
        List<List<Integer>> result4 = zigzagLevelOrder2(root1);
        System.out.println(result3);
        System.out.println(result4);
        assertEquals(result3, result4);
    }
}
