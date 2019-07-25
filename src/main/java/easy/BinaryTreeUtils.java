package easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 7/24/19.
 * Simple tree utility methods that can be reused
 */
public class BinaryTreeUtils {
    // logic: simple BFS: get the queue size at each level, process and add them in to separate lists
    protected static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("No elements in the tree.");
        }
        Queue<TreeNode> nodeStoreQueue = new LinkedList<>();
        nodeStoreQueue.offer(root);

        while (!nodeStoreQueue.isEmpty()) {
            int queueSize = nodeStoreQueue.size();   // save the current queue size

            for (int i = 0; i < queueSize; i++) {
                TreeNode temp = nodeStoreQueue.poll();
                System.out.print(temp.val + " ");

                if (temp.left != null) {
                    nodeStoreQueue.offer(temp.left);
                }
                if (temp.right != null) {
                    nodeStoreQueue.offer(temp.right);
                }
            }
        }
    }
}
