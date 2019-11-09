package easy;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 11/9/19.
 * <p>
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * </p>
 */
public class _107_BinaryTree_LevelOrder_Traversal_II {
    // simple BFS: the only trick is that as you process each and every level of the tree, add the elements from the current level to the FIRST INDEX of the result list, so by the end of the process, you will have the last level elements at the beginning of the result list and first level elements at the end of the result list
    // TC: O(n)
    private static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            result.add(0, list);
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

        List<List<Integer>> expected = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(15, 7);
        List<Integer> list2 = Arrays.asList(9, 20);
        List<Integer> list3 = Arrays.asList(3);
        expected.add(list1);
        expected.add(list2);
        expected.add(list3);

        assertEquals(levelOrderBottom(root), expected);
    }
}