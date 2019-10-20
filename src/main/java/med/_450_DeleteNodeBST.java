package med;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by udaythota on 5/29/19.
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 */
public class _450_DeleteNodeBST {
    private static TreeNode deleteNode(TreeNode root, int key) {
        return null;
        // FIXME: https://leetcode.com/problems/delete-node-in-a-bst/discuss/93328/Java-Easy-to-Understand-Solution
        // FIXME: https://leetcode.com/problems/delete-node-in-a-bst/discuss/252046/Java-3ms-Easy-Iteration
    }


    private static List<Integer> levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            List<Integer> outputList = new ArrayList<>();
            Queue<TreeNode> nodeStoreQueue = new LinkedList<>();
            nodeStoreQueue.offer(root);
            while (!nodeStoreQueue.isEmpty()) {
                TreeNode temp = nodeStoreQueue.poll();
                outputList.add(temp.val);
                if (temp.left != null)
                    nodeStoreQueue.offer(temp.left);
                if (temp.right != null)
                    nodeStoreQueue.offer(temp.right);
            }
            return outputList;
        }
    }

    public static void main(String[] args) {
        // test case: 1
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node4.right = node5;

        TreeNode rootAfterDel = deleteNode(root, 3);
        System.out.println(levelOrderTraversal(rootAfterDel));
    }
}
