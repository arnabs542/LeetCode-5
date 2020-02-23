package easy;

import java.util.LinkedList;
import java.util.Queue;

import static org.testng.Assert.assertEquals;

/**
 * Created by z002jsf on 1/31/20.
 * <p>
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 * </p>
 */
public class _671_Second_Min_Node_BT {
    // core logic: BFS approach: with the given conditions, we know that the root should be the first min value and hence while performing BFS, when you encounter a first min element greater than root.val, that should be the 2nd min element
    // TC: O(n)
    private static int findSecondMinimumValue(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int firstMin = root.val;
        Integer secondMin = null;

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            if (temp.val != firstMin) {     // don't consider the first min value: we are concerned only about 2nd min
                if (secondMin == null) {
                    secondMin = temp.val;
                } else {
                    secondMin = Math.min(secondMin, temp.val);
                }
            }

            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return secondMin == null ? -1 : secondMin;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node5Dup = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);

        root.left = node2;
        root.right = node5;
        node5.left = node5Dup;
        node5.left = node7;

        assertEquals(findSecondMinimumValue(root), 5);
    }
}