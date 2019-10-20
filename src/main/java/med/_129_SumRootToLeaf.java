package med;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 5/28/19.
 * <p>
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * Input: [1,2,3]
 * 1
 * / \
 * 2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * <p>
 * </p>
 */
public class _129_SumRootToLeaf {

    // Simple recursive approach
    private static int sumNumbersRecursive(TreeNode root) {
        return recursiveHelper(root, 0);
    }

    private static int recursiveHelper(TreeNode root, int currentSum) {
        if (root == null) {
            return 0;
        }
        currentSum = currentSum * 10 + root.val;    // calculate the current sum for every node

        if (root.left == null && root.right == null) {  // if its a leaf node return the current sum
            return currentSum;
        }
        return recursiveHelper(root.left, currentSum) + recursiveHelper(root.right, currentSum);   // return the sum of left child sum and right child sum
    }

    // No Recursive solution using simple BFS approach: calculate the current node sum for all the non leaf nodes and save it . when its a leaf node, return the current sum (left child sum + right child sum)
    // TC: O(N) as every element in the tree is visited only once
    private static int sumNumbers(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodeStoreQueue = new LinkedList<>();
        Queue<Integer> sumCountQueue = new LinkedList<>();
        nodeStoreQueue.offer(root);
        sumCountQueue.offer(root.val);

        while (!nodeStoreQueue.isEmpty()) {
            TreeNode current = nodeStoreQueue.poll();
            int currentSum = sumCountQueue.poll();

            if (current.left == null && current.right == null) {
                result += currentSum;
            }

            if (current.left != null) {
                nodeStoreQueue.offer(current.left);
                sumCountQueue.offer((currentSum * 10) + current.left.val);
            }
            if (current.right != null) {
                nodeStoreQueue.offer(current.right);
                sumCountQueue.offer((currentSum * 10) + current.right.val);
            }
        }
        return result;
    }

    // Alternate non recursive solution
    // Similar BFS approach as the above solution, except that we keep modifying the values of every node (instead of saving them to a queue) making this a LEAST PREFERRED solution
    private static int sumNumbersAlternate(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodeStoreQueue = new LinkedList<>();
        nodeStoreQueue.offer(root);

        while (!nodeStoreQueue.isEmpty()) {
            TreeNode current = nodeStoreQueue.poll();
            int currentSum = current.val;

            if (current.left != null) {
                current.left.val = (currentSum * 10) + current.left.val;
                nodeStoreQueue.offer(current.left);
            }
            if (current.right != null) {
                current.right.val = (currentSum * 10) + current.right.val;
                nodeStoreQueue.offer(current.right);
            }

            if (current.left == null && current.right == null) {
                result += currentSum;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        // tech case: 1
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumNumbers(root));
        System.out.println(sumNumbersRecursive(root));
        System.out.println(sumNumbersAlternate(root));

        // tech case: 2
        TreeNode root1 = new TreeNode(4);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);

        root1.left = node1;
        root1.right = node2;
        node1.left = node3;
        node1.right = node4;
        System.out.println(sumNumbers(root1));
        System.out.println(sumNumbersRecursive(root1));
        System.out.println(sumNumbersAlternate(root1));
    }
}
