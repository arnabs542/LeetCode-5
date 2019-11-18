package easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 11/15/19.
 * <p>
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 * </p>
 */
public class _617_Merge_2_Binary_Trees {
    // simple recursion (using pre order traversal): by creating a new tree node
    // TC: O(m) -> where m represents the minimum number of nodes between 2 given trees as those are the ones that need to be traversed
    // SC: O(m) -> since the depth of the recursion tree can go up to m in the case of a skewed tree. In average case, depth will be O(log m)
    private static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        TreeNode t3 = new TreeNode(t1.val + t2.val);
        t3.left = mergeTrees(t1.left, t2.left);
        t3.right = mergeTrees(t1.right, t2.right);
        return t3;
    }

    // simple recursion (using pre order traversal): in place solution: both trees merged to the 1st tree
    private static TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    // iterative approach: may not be very intuitive. use the above recursive approaches if possible
    private static TreeNode mergeTreesIterative(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{t1, t2});

        while (!queue.isEmpty()) {
            TreeNode[] temp = queue.poll();
            TreeNode n1 = temp[0];
            TreeNode n2 = temp[1];

            if (n1 == null || n2 == null) {
                continue;
            } else {
                n1.val += n2.val;
            }

            if (n1.left == null) {
                n1.left = n2.left;
            } else {
                queue.add(new TreeNode[]{n1.left, n2.left});
            }

            if (n1.right == null) {
                n1.right = n2.right;
            } else {
                queue.add(new TreeNode[]{n1.right, n2.right});
            }
        }
        return t1;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        root1.left = node3;
        root1.right = node2;
        node3.left = node5;

        TreeNode root2 = new TreeNode(2);
        TreeNode node21 = new TreeNode(1);
        TreeNode node23 = new TreeNode(3);
        TreeNode node24 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        root2.left = node21;
        root2.right = node23;
        node21.right = node24;
        node23.right = node7;

        TreeNode newRoot = mergeTrees(root1, root2);
        BinaryTreeUtils.printLevelOrder(newRoot);
    }
}