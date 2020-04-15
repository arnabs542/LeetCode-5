package med;

import org.testng.Assert;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by udaythota on 4/14/20.
 * <p>
 * Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 * </p>
 */
public class _Two_Sum_BSTs {
    // core logic: BFS: similar to 2 sum: first navigate the first tree and add all the elements to a set. then navigate the second tree and see if target - node value is present in the set
    // TC: O(m + n) -> where m is the number of elements in the first tree and n is the number of elements in the second tree
    private static boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Queue<TreeNode> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(root1);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            set.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        queue.clear();
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (set.contains(target - node.val)) {
                return true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    // exact same as above approach but we use DFS here (instead of BFS)
    // TC: O(m + n) -> where m is the number of elements in the first tree and n is the number of elements in the second tree
    private static boolean twoSumBSTs2(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> set = new HashSet<>();
        dfs(root1, set);
        return dfs2(root2, set, target);
    }

    // add elements from first tree to the set
    private static void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }

    // navigate 2nd tree to see if target - node val is present in the set
    private static boolean dfs2(TreeNode root, Set<Integer> set, int target) {
        if (root == null) {
            return false;
        }
        if (set.contains(target - root.val)) {
            return true;
        }
        if (dfs2(root.left, set, target) || dfs2(root.right, set, target)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        root1.left = node1;
        root1.right = node4;

        TreeNode root2 = new TreeNode(1);
        TreeNode node0 = new TreeNode(0);
        TreeNode node3 = new TreeNode(3);
        root2.left = node0;
        root2.right = node3;
        Assert.assertTrue(twoSumBSTs(root1, root2, 5));
        Assert.assertTrue(twoSumBSTs2(root1, root2, 5));
    }
}
