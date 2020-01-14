package med;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by udaythota on 5/27/19.
 * Given a binary tree, return the preorder traversal of its nodes' values.
 */
public class _144_PreOrderTraversal {
    List<Integer> resultList = new ArrayList<>();

    // Trivial: Pre-Order traversal recursive
    private List<Integer> preOrderTraversal(TreeNode root) {
        if (root != null) {
            resultList.add(root.val);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
        return resultList;
    }

    // Pre-Order traversal Iterative: uses a stack to store the nodes while traversing
    // as the left node has to be processed first: push the right node and then the left node , which makes the left node come out first
    // see the below alternate solution to be consistent with other traversal approaches
    private List<Integer> preOrderTraversalIterative(TreeNode root) {
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        nodeStoreStack.push(root);

        while (!nodeStoreStack.isEmpty()) {
            TreeNode current = nodeStoreStack.pop();
            resultList.add(current.val);

            if (current.right != null) {
                nodeStoreStack.push(current.right);
            }
            if (current.left != null) {
                nodeStoreStack.push(current.left);
            }
        }
        return resultList;
    }

    // this approach is similar to the in-order iterative traversal, so easy to remember
    private List<Integer> preOrderTraversalAlternate(TreeNode root) {
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        TreeNode current = root;

        while (current != null || !nodeStoreStack.isEmpty()) {
            if (current != null) {
                resultList.add(current.val);   // process the node itself (print the value)
                nodeStoreStack.push(current);  // save the node
                current = current.left;   // process the left child
            } else {
                current = nodeStoreStack.pop();
                current = current.right;  // process the right child
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

        List<Integer> resultList = new _144_PreOrderTraversal().preOrderTraversal(root);
        System.out.println(resultList);

        List<Integer> resultList1 = new _144_PreOrderTraversal().preOrderTraversalIterative(root);
        System.out.println(resultList1);

        List<Integer> resultList2 = new _144_PreOrderTraversal().preOrderTraversalAlternate(root);
        System.out.println(resultList2);
    }
}
