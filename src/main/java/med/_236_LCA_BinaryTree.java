package med;

import java.util.*;

/**
 * Created by udaythota on 6/2/19.
 * <p>
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * </p>
 */
public class _236_LCA_BinaryTree {
    // watch this: https://www.youtube.com/watch?v=13m9ZCB8gjw if you have more questions
    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    // Non Recursive BFS Approach: TC - O(n)
    // Step 1: traverse all the tree (using level order traversal here) and save the node-parent pairs (in a hash map) for all the nodes in the tree (till the point we encounter BOTH p and q)
    // Step 2: start from p and save all the parents of p till root (including p and root) in a hash set
    // Step 3: start from q and and navigate through all the parents till root and whenever you see a parent that is present in the set (common ancestor for p and q), that is LCA
    private static TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>(); // look up map for the node and its immediate parent
        Queue<TreeNode> queue = new LinkedList<>();
        parentMap.put(root, null);
        queue.add(root);
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode node = queue.poll();
            parentMap.put(node.left, node);  // save the node and its parentMap
            parentMap.put(node.right, node); // save the node and its parentMap

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        Set<TreeNode> set = new HashSet<>();
        while (p != null) {   // get all the parents of p till the root
            set.add(p);
            p = parentMap.get(p);
        }
        while (!set.contains(q)) {   // starting q, get all its parents till the root and in the process, whenever you see the parentMap that is already present in the set, that's the first common parentMap (which is LCA)
            q = parentMap.get(q);
        }
        return q;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;
        TreeNode lca = lowestCommonAncestor(root, node1, node2);
        System.out.println(lca.val);

        TreeNode lca1 = lowestCommonAncestorIterative(root, node1, node2);
        System.out.println(lca1.val);
    }
}