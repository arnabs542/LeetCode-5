package _5_Longest_Palindromic_Substring;

import java.util.*;

/**
 * Created by udaythota on 5/27/19.
 * Given a binary tree, return the inorder traversal of its nodes values.
 */
public class _94_InOrderTraversal {
    List<Integer> resultList = new ArrayList<>();

    // Trivial: In-Order traversal recursive
    private List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            resultList.add(root.val);
            inorderTraversal(root.right);
        }
        return resultList;
    }

    // In-Order traversal Iterative: uses a stack to store the nodes while traversing
    private List<Integer> inorderTraversalIterative(TreeNode root) {
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        TreeNode current = root;

        while (current != null || !nodeStoreStack.isEmpty()) {
            if (current != null) {
                nodeStoreStack.push(current);
                current = current.left;
            } else {
                current = nodeStoreStack.pop();
                resultList.add(current.val);
                current = current.right;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.right = node1;
        node1.left = node2;

        List<Integer> resultList = new _94_InOrderTraversal().inorderTraversal(root);
        System.out.println(resultList);

        List<Integer> resultList1 = new _94_InOrderTraversal().inorderTraversalIterative(root);
        System.out.println(resultList1);
    }
}