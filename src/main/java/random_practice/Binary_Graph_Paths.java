package random_practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udaythota on 3/15/20.
 * <p>
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * Note: A leaf is a node with no children.
 * </p>
 */
public class Binary_Graph_Paths {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // TC: O(N) - where N is the number of nodes in the tree. This is because we only visit all nodes in the tree only once.
    private List<List<Integer>> pathSumRecursive(TreeNode root, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        recursiveHelper(root, resultList, new ArrayList<Integer>(), target);
        return resultList;
    }

    private void recursiveHelper(TreeNode root, List<List<Integer>> resultList, List<Integer> tempList, int target) {
        if (root == null) {
            return;
        }
        tempList.add(root.val);

        if (root.left == null && root.right == null && root.val == target) {   // when it is a leaf node and the current sum is zero, add the temp list elements to the result set
            resultList.add(tempList);
            return;
        }
        recursiveHelper(root.left, resultList, new ArrayList<>(tempList), target);   // IMPORTANT: make sure to have a new array list for temp
        recursiveHelper(root.right, resultList, new ArrayList<>(tempList), target);  // IMPORTANT: make sure to have a new array list for temp
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);
        TreeNode node10 = new TreeNode(15);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node10;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        node4.left = node10;

        node5.left = node8;
        node5.right = node9;

        List<List<Integer>> resultList = new Binary_Graph_Paths().pathSumRecursive(root, 15);
        // List<List<Integer>> resultList1 = new Binary_Graph_Paths().pathSumRecursive(root, 27);
        System.out.println(resultList);
        // System.out.println(resultList1);
    }
}
