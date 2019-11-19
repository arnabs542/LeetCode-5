package med;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by udaythota on 5/28/19.
 * Given a binary tree, return the level order traversal of its nodes values. (ie, from left to right, level by level).
 */
public class _102_LevelOrderTraversal {
    List<List<Integer>> resultList = new ArrayList<>();

    // logic: simple BFS: get the queue size at each level, process and add them in to separate lists
    private List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> nodeStoreQueue = new LinkedList<>();
        nodeStoreQueue.offer(root);

        while (!nodeStoreQueue.isEmpty()) {
            List<Integer> innerList = new ArrayList<>();
            int queueSize = nodeStoreQueue.size();   // save the current queue size

            for (int i = 0; i < queueSize; i++) {
                TreeNode temp = nodeStoreQueue.poll();
                innerList.add(temp.val);

                if (temp.left != null) {
                    nodeStoreQueue.offer(temp.left);
                }
                if (temp.right != null) {
                    nodeStoreQueue.offer(temp.right);
                }
            }
            resultList.add(innerList);
        }
        return resultList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        List<List<Integer>> resultList = new _102_LevelOrderTraversal().levelOrder(root);
        System.out.println(resultList);
    }
}