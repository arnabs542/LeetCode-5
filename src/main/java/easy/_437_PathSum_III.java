package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 11/16/19.
 * <p>
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * </p>
 */
public class _437_PathSum_III {
    // simple recursion: NOT a great solution as you keep iterating the same paths over and over again
    // see the below commented solution (which is more optimized) and brainstorm later
    // TC: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
    // SC: O(n) due to recursion
    private static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumHelper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private static int pathSumHelper(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        return (node.val == sum ? 1 : 0) + pathSumHelper(node.left, sum - node.val) + pathSumHelper(node.right, sum - node.val);
    }

   /* private static int count = 0;
    public static int pathSum(TreeNode root, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // for special case that starting node is root
        pathSumHelper(map, root, 0, target);
        return count;
    }

    private static void pathSumHelper(Map<Integer, Integer> prefixSumMap, TreeNode root, int sum, int target) {
        if (root == null) {
            return;
        }
        sum += root.val;
        count += prefixSumMap.getOrDefault(sum - target, 0);
        prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        pathSumHelper(prefixSumMap, root.left, sum, target);
        pathSumHelper(prefixSumMap, root.right, sum, target);
        prefixSumMap.put(sum, prefixSumMap.get(sum) - 1);
    }*/

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3Neg = new TreeNode(-3);
        TreeNode node3First = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node11 = new TreeNode(11);
        TreeNode node3Second = new TreeNode(3);
        TreeNode node2Neg = new TreeNode(-2);
        TreeNode node1 = new TreeNode(1);
        root.left = node5;
        root.right = node3Neg;
        node5.left = node3First;
        node5.right = node2;
        node3Neg.right = node11;
        node3First.left = node3Second;
        node3First.right = node2Neg;
        node2.right = node1;

        assertEquals(pathSum(root, 8), 3);
    }
}