package med;

import org.testng.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 4/13/20.
 * <p>
 * Given a binary tree, determine if it is a complete binary tree.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * </p>
 */
public class _958_Check_Completeness_BT {
    // simple BFS: the fact that in a complete BT, all the null nodes should be as left as possible means that, once we encounter a null node there CANNOT be non null node appearing after that in the level order traversal
    // so the first time we encounter a null node, set the boolean flag to true and in case we encounter a non null node when the flag is true means that the BT is not complete
    // TC: O(n)
    private static boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isNull = false;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                isNull = true;   // encountered the null node for the first time: set isNull to true
            } else {
                queue.offer(temp.left);
                queue.offer(temp.right);
            }

            if (temp != null && isNull) {   // the fact that we got a non null node after a null node means its not a valid complete BT (for a complete BT: null nodes are in the very end)
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        Assert.assertTrue(isCompleteTree(root));
    }
}
