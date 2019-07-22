package med;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by udaythota on 5/28/19.
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 */
public class _113_PathSum_II {

    private List<List<Integer>> pathSumRecursive(TreeNode root, int sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        recursiveHelper(root, sum, resultList, new ArrayList<Integer>());
        return resultList;
    }

    private void recursiveHelper(TreeNode root, int sum, List<List<Integer>> resultList, List<Integer> tempList) {
        if (root == null) {
            return;
        }
        tempList.add(root.val);
        sum = sum - root.val;

        if (root.left == null && root.right == null && sum == 0) {   // when it is a leaf node and the current sum is zero, add the temp list elements to the result set
            resultList.add(tempList);
            return;
        }
        recursiveHelper(root.left, sum, resultList, new ArrayList<>(tempList));
        recursiveHelper(root.right, sum, resultList, new ArrayList<>(tempList));
    }

    // FIXME: complete the iterative solution too
    private List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> nodeStoreQueue = new LinkedList<>();
        nodeStoreQueue.offer(root);

        while (!nodeStoreQueue.isEmpty()) {

        }
        return resultList;
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

        root.left = node1;
        root.right = node2;
        node1.left = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        node5.left = node8;
        node5.right = node9;

        List<List<Integer>> resultList = new _113_PathSum_II().pathSumRecursive(root, 22);
        List<List<Integer>> resultList1 = new _113_PathSum_II().pathSumRecursive(root, 27);
        System.out.println(resultList);
        System.out.println(resultList1);
    }
}
