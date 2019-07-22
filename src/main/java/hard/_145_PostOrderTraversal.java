package hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by udaythota on 5/28/19.
 * Given a binary tree, return the postorder traversal of its nodes' values.
 */
public class _145_PostOrderTraversal {
    private List<Integer> resultList = new ArrayList<>();

    // Trivial: Post-Order traversal recursive
    private List<Integer> postOrderTraversal(TreeNode root) {
        if (root != null) {
            postOrderTraversal(root.right);
            postOrderTraversal(root.left);
            resultList.add(root.val);
        }
        return resultList;
    }

    // post order iterative: uses stack to save the nodes and LINKED LIST to save the result
    // logic: reverse the pre-order approach and make sure whenever we add a node, we add it as the first element in the list (addFirst)
    // when you addFirst the root, right and the left child, the order of result list would be: left child, right child and the root
    private List<Integer> postOrderIterative(TreeNode root) {
        LinkedList<Integer> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        TreeNode current = root;

        while (current != null || !nodeStoreStack.isEmpty()) {
            if (current != null) {
                resultList.addFirst(current.val);  // reverse the process of pre-order
                nodeStoreStack.push(current);
                current = current.right;  // reverse the process of pre-order
            } else {
                current = nodeStoreStack.pop();
                current = current.left;   // reverse the process of pre-order
            }
        }
        return resultList;
    }

    // post order iterative: similar to the above approach except that we use add an array list (instead of a linked list) and add elements to the first index (instead of using the addFirst function)
    // whenever adding an element to the result list, add it as the first element, so the root, left and right child when added to result list will process as (left child, right child and root)
    private List<Integer> postOrderTraversalAlternate(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> nodeStoreStack = new Stack<>();
        nodeStoreStack.push(root);

        while (!nodeStoreStack.isEmpty()) {
            TreeNode curr = nodeStoreStack.pop();
            resultList.add(0, curr.val);
            if (curr.left != null) {
                nodeStoreStack.push(curr.left);
            }
            if (curr.right != null) {
                nodeStoreStack.push(curr.right);
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

        List<Integer> resultList = new _145_PostOrderTraversal().postOrderTraversal(root);
        System.out.println(resultList);

        List<Integer> resultList1 = new _145_PostOrderTraversal().postOrderIterative(root);
        System.out.println(resultList1);

        List<Integer> resultList2 = new _145_PostOrderTraversal().postOrderTraversalAlternate(root);
        System.out.println(resultList2);
    }
}
